package com.sonder.codechallenge.ui

import androidx.lifecycle.SavedStateHandle
import com.sonder.codechallenge.ui.util.MainDispatcherRule
import com.sonder.data.repositories.SearchRepositoryImpl
import com.sonder.domain.usecases.search.ClearSearchResultsUseCase
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {

	@get:Rule
	val dispatcherRule = MainDispatcherRule()

	private val searchRepository = SearchRepositoryImpl()
	private val clearSearchResultsUseCase = ClearSearchResultsUseCase(searchRepository)
	private val getSectionSearchResultsUseCase = GetSectionSearchResultsUseCase(searchRepository)

	private lateinit var viewModel: MainActivityViewModel

	@Before
	fun setup() {
		viewModel = MainActivityViewModel(
			savedStateHandle = SavedStateHandle(),
			clearSearchResultsUseCase = clearSearchResultsUseCase,
			getSectionSearchResultsUseCase = getSectionSearchResultsUseCase,
		)
	}

	@Test
	fun stateIsInitiallyStarted() = runTest {
		assertEquals(SearchActivityStates.Started, viewModel.state.value)
	}

	@Test
	fun updateSearchQuery() {
	}

	@Test
	fun clearSearchQuery() {
	}
}