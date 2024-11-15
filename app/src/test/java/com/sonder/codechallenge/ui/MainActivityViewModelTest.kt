package com.sonder.codechallenge.ui

import androidx.lifecycle.SavedStateHandle
import com.sonder.codechallenge.ui.util.MainDispatcherRule
import com.sonder.data.models.SearchItemViewType
import com.sonder.data.repositories.SearchRepositoryImpl
import com.sonder.domain.usecases.search.ClearSearchResultsUseCase
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
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
	fun stateIsLoaded_withMockSearchQuery() = runTest {
		// Arrange
		val query = "mock"
		val expectedStateLoading = SearchActivityStates.Loading(query)
		val expectedStateLoaded = SearchActivityStates.Loaded(
			query = query,
			// Must be in this order
			// VERTICAL_COMPACT should not be included because the result contains an empty list
			searchItemViewTypes = listOf(
				SearchItemViewType.HORIZONTAL_COMPACT,
				SearchItemViewType.HORIZONTAL_DETAILED,
				SearchItemViewType.VERTICAL_DETAILED
			),
		)

		// Act
		viewModel.updateSearchQuery(query)

		// Assert
		val actualStates = viewModel.state.take(2).toList()
		assertEquals(expectedStateLoading, actualStates[0])
		assertEquals(expectedStateLoaded, actualStates[1])
	}

	@Test
	fun stateIsLoaded_withTestSearchQuery() = runTest {
		// Arrange
		val query = "test"
		val expectedStateLoading = SearchActivityStates.Loading(query)
		val expectedStateLoaded = SearchActivityStates.Loaded(
			query = query,
			searchItemViewTypes = emptyList(),
		)

		// Act
		viewModel.updateSearchQuery(query)

		// Assert
		val actualStates = viewModel.state.take(2).toList()
		assertEquals(expectedStateLoading, actualStates[0])
		assertEquals(expectedStateLoaded, actualStates[1])
	}

	@Test
	fun stateIsError_withErrorSearchQuery() = runTest {
		// Arrange
		val query = "error"
		val expectedStateLoading = SearchActivityStates.Loading(query)
		val expectedStateError = SearchActivityStates.Error

		// Act
		viewModel.updateSearchQuery(query)

		// Assert
		val actualStates = viewModel.state.take(2).toList()
		assertEquals(expectedStateLoading, actualStates[0])
		assertEquals(expectedStateError, actualStates[1])
	}

	@Test
	fun clearSearchQuery() {
	}
}