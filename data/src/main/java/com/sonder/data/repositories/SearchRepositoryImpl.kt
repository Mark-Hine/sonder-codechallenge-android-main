package com.sonder.data.repositories

import com.sonder.data.MockRequests
import com.sonder.data.MockResponses
import com.sonder.data.models.SearchRequestParams
import com.sonder.data.models.SearchSectionResult
import com.sonder.data.models.applyParamFilters
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {

    private val _searchQuery = MutableStateFlow("")
    private val sectionSearchResultsCache = mutableMapOf<SearchRequestParams, SearchSectionResult>()
    private val sectionSearchResultsMap = mapOf(
        MockRequests.horizontalCompactRequestParams to MockResponses.getHorizontalCompactSearchResults(),
        MockRequests.verticalCompactRequestParams to MockResponses.getVerticalCompactSearchResults(),
        MockRequests.horizontalDetailedRequestParams to MockResponses.getHorizontalDetailedSectionResults(),
        MockRequests.verticalDetailedRequestParams to MockResponses.getVerticalDetailedSectionResults(),
    )

    override val searchQuery: Flow<String> = _searchQuery.asStateFlow()

    override fun clearSectionSearchResults() {
        sectionSearchResultsCache.clear()
    }

    override fun getSectionSearchResults(params: SearchRequestParams): Flow<SearchSectionResult> = flow {
        val cachedResult = sectionSearchResultsCache[params]
        if (cachedResult != null) {
            emit(cachedResult)
        } else {
            // Simulate network delay
            delay(2000)

            // Check for the "error" query to simulate an error response
            if (params == MockRequests.errorRequestParams) {
                throw MockResponses.getErrorResult().first()
            }

            // Get the search result for the params and apply filters
            val result = getSearchResult(params).applyParamFilters(params)

            // Update the search query
            _searchQuery.update { params.query }

            // Cache the result
            sectionSearchResultsCache[params] = result

            // Emit the result
            emit(result)
        }
    }

    /**
     * Gets the [SearchSectionResult] for the [SearchRequestParams].
     *
     * @param params The search request parameters to get the result for.
     *
     * @return The [SearchSectionResult] or an empty result if not found.
     */
    private suspend fun getSearchResult(params: SearchRequestParams): SearchSectionResult {
        // Get the section search result for the params
        val searchSectionResult = sectionSearchResultsMap[params]?.first()

        // Return the section search result or an empty result
        return searchSectionResult ?: SearchSectionResult(
            sectionTitle = params.sectionTitle,
            sectionDescription = null,
            items = emptyList()
        )
    }

}