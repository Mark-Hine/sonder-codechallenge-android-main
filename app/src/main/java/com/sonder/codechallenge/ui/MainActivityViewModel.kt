package com.sonder.codechallenge.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sonder.codechallenge.ui.base.BaseViewModel
import com.sonder.common.result.Result
import com.sonder.common.result.asResult
import com.sonder.data.models.SearchItemViewType
import com.sonder.domain.usecases.search.ClearSearchResultsUseCase
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val clearSearchResultsUseCase: ClearSearchResultsUseCase,
	private val getSectionSearchResultsUseCase: GetSectionSearchResultsUseCase,
) : BaseViewModel<SearchActivityStates>(SearchActivityStates.Started, savedStateHandle) {

	private var getAllSectionSearchResultsJob: Job? = null

	fun updateSearchQuery(query: String) {
		clearSearchResults()
		getSectionSearchResults(query)
	}

	fun clearSearchQuery() {
		clearSearchResults()
		updateState(SearchActivityStates.Started)
	}

	private fun clearSearchResults() {
		viewModelScope.launch {
			clearSearchResultsUseCase.execute(Unit)
		}
	}

	private fun getSectionSearchResults(query: String) {
		// Search all the sections by using the SearchItemViewType enum
		val allSectionSearchResultsFlows = SearchItemViewType.entries.map { searchItemViewType ->
			val params = GetSectionSearchResultsUseCase.Params(searchItemViewType, query)
			getSectionSearchResultsUseCase.execute(params).map { result ->
				// Map the params to the result so we know which section the result belongs to
				params to result
			}
		}

		// Cancel previous job
		getAllSectionSearchResultsJob?.cancel()

		// Combine all the flows and update the state
		getAllSectionSearchResultsJob = combine(allSectionSearchResultsFlows) { it.toList() }
			.asResult()
			.onEach { result ->
				when (result) {
					is Result.Loading -> {
						updateState(SearchActivityStates.Loading(query))
					}

					is Result.Success -> {
						val searchItemViewTypes = result.data
							// Filter out the results without any items
							.filter { it.second.items.isNotEmpty() }
							// Map the results to the SearchItemViewType
							.map { it.first.searchItemViewType }
							// Sort the results by the order of the enum
							.sorted()
						updateState(SearchActivityStates.Loaded(query, searchItemViewTypes))
					}

					is Result.Error -> {
						updateState(SearchActivityStates.Error)
					}
				}
			}.launchIn(viewModelScope)
	}

}