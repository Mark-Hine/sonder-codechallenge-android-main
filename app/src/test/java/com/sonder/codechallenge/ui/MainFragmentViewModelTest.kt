package com.sonder.codechallenge.ui

import androidx.lifecycle.SavedStateHandle
import com.sonder.codechallenge.ui.util.MainDispatcherRule
import com.sonder.data.models.SearchItemViewType
import com.sonder.data.repositories.SearchRepositoryImpl
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import com.sonder.domain.usecases.search.SubscribeToSearchQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainFragmentViewModelTest {

	@get:Rule
	val dispatcherRule = MainDispatcherRule()

	private val savedStateHandle = SavedStateHandle()
	private val searchRepository = SearchRepositoryImpl()
	private val subscribeToSearchQueryUseCase = SubscribeToSearchQueryUseCase(searchRepository)
	private val getSectionSearchResultsUseCase = GetSectionSearchResultsUseCase(searchRepository)

	private lateinit var viewModel: MainFragmentViewModel

	@Before
	fun setup() {
		savedStateHandle[SEARCH_ITEM_VIEW_TYPE] = SearchItemViewType.HORIZONTAL_COMPACT
		viewModel = MainFragmentViewModel(
			savedStateHandle = savedStateHandle,
			subscribeToSearchQueryUseCase = subscribeToSearchQueryUseCase,
			getSectionSearchResultsUseCase = getSectionSearchResultsUseCase,
		)
	}

	@Test
	fun stateIsInitiallyStarted() = runTest {
		assertEquals(SearchFragmentStates.Started, viewModel.state.value)
	}

	@Test
	fun getSectionSearchResults() {
	}
}