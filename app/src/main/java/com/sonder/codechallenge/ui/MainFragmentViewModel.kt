package com.sonder.codechallenge.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sonder.codechallenge.ui.base.BaseViewModel
import com.sonder.common.result.Result
import com.sonder.common.result.asResult
import com.sonder.data.models.SearchItemViewType
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import com.sonder.domain.usecases.search.SubscribeToSearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val subscribeToSearchQueryUseCase: SubscribeToSearchQueryUseCase,
	private val getSectionSearchResultsUseCase: GetSectionSearchResultsUseCase,
) : BaseViewModel<SearchFragmentStates>(SearchFragmentStates.Started, savedStateHandle) {

	val searchItemViewType: SearchItemViewType = requireNotNull(savedStateHandle[SEARCH_ITEM_VIEW_TYPE])
	private var getSectionSearchResultsJob: Job? = null

	fun onCreate() {
		subscribeToSearchQueryUseCase.execute(Unit)
			.filter { it.isNotEmpty() }
			.onEach {
				getSectionSearchResults(it)
			}
			.launchIn(viewModelScope)
	}

	@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
	fun getSectionSearchResults(query: String) {
		// Get the search results for the given section
		val params = GetSectionSearchResultsUseCase.Params(
			searchItemViewType = searchItemViewType,
			query = query,
		)

		// Cancel previous job
		getSectionSearchResultsJob?.cancel()

		// Get the search results for the given section
		getSectionSearchResultsJob = getSectionSearchResultsUseCase.execute(params)
			.asResult()
			.onEach { result ->
				when (result) {
					is Result.Success -> {
						val (sectionTitle, sectionDescription, items) = result.data
						updateState(
							SearchFragmentStates.Loaded(
								sectionTitle = sectionTitle,
								sectionDescription = sectionDescription,
								searchItemViewType = searchItemViewType,
								adapterItems = items,
							)
						)
					}

					else -> Unit
				}
			}.launchIn(viewModelScope)
	}

}

internal const val SEARCH_ITEM_VIEW_TYPE = "searchItemViewType"