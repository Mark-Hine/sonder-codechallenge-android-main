package com.sonder.codechallenge.ui

import androidx.lifecycle.SavedStateHandle
import com.sonder.codechallenge.ui.util.MainDispatcherRule
import com.sonder.data.MockRequests
import com.sonder.data.MockResponses
import com.sonder.data.models.SearchItemViewType
import com.sonder.data.models.applyParamFilters
import com.sonder.data.repositories.SearchRepositoryImpl
import com.sonder.domain.usecases.search.GetSectionSearchResultsUseCase
import com.sonder.domain.usecases.search.SubscribeToSearchQueryUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
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
	fun stateIsLoaded_withMockSearchQuery_andHorizontalCompactViewType() = runTest {
		// Arrange
		val query = "mock"
		val request = MockRequests.horizontalCompactRequestParams
		val result = MockResponses.getHorizontalCompactSearchResults().first().applyParamFilters(request)
		val expectedStateLoaded = SearchFragmentStates.Loaded(
			sectionTitle = result.sectionTitle,
			sectionDescription = result.sectionDescription,
			searchItemViewType = SearchItemViewType.HORIZONTAL_COMPACT,
			adapterItems = result.items,
		)

		// Act
		viewModel.getSectionSearchResults(query)

		// Assert
		val actualState = viewModel.state.take(2).last()
		assertEquals(expectedStateLoaded, actualState)
	}

	@Test
	fun applyParamFilters_filtersItemsCorrectly() = runTest {
		// Arrange
		val request = MockRequests.horizontalCompactRequestParams
		val result = MockResponses.getHorizontalCompactSearchResults().first()
		val expectedContentTypes = request.contentTypes
		val expectedSize = request.size

		// Act
		val filteredResult = result.applyParamFilters(request)

		// Assert
		val actualSize = filteredResult.items.size
		assertEquals(expectedSize, actualSize)
		val actualContentTypes = filteredResult.items.map { it.contentType?.value }.distinct()
		assertEquals(expectedContentTypes, actualContentTypes)
	}

}