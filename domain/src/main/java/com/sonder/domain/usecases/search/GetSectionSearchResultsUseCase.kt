package com.sonder.domain.usecases.search

import com.sonder.data.MockRequests
import com.sonder.data.models.SearchItemViewType
import com.sonder.data.models.SearchRequestParams
import com.sonder.data.models.SearchSectionResult
import com.sonder.data.repositories.SearchRepository
import com.sonder.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSectionSearchResultsUseCase @Inject constructor(
	private val repository: SearchRepository,
) : FlowUseCase<GetSectionSearchResultsUseCase.Params, SearchSectionResult>() {

	private val mockRequestParams = mapOf(
		SearchItemViewType.HORIZONTAL_DETAILED to MockRequests.horizontalDetailedRequestParams,
		SearchItemViewType.HORIZONTAL_COMPACT to MockRequests.horizontalCompactRequestParams,
		SearchItemViewType.VERTICAL_COMPACT to MockRequests.verticalCompactRequestParams,
		SearchItemViewType.VERTICAL_DETAILED to MockRequests.verticalDetailedRequestParams,
	)

	data class Params(
		val searchItemViewType: SearchItemViewType,
		val query: String,
	)

	override fun construct(params: Params): Flow<SearchSectionResult> {
		val requestParams = getSearchRequestParams(params)
		return repository.getSectionSearchResults(requestParams)
	}

	/**
	 * Gets the [SearchRequestParams] that matches the [Params].
	 *
	 * @param params The [Params] to match.
	 *
	 * @return The [SearchRequestParams] that matches the [Params] or a new [SearchRequestParams] if no match is found.
	 */
	private fun getSearchRequestParams(params: Params): SearchRequestParams {
		// Return error request params if the query is "error"
		return if (params.query.equals("error", ignoreCase = true)) {
			MockRequests.errorRequestParams
		} else {
			// Return the mock request params if found and the query matches, otherwise return a new search request params
			val requestParams = mockRequestParams[params.searchItemViewType].takeIf { it?.query == params.query }
			requestParams ?: SearchRequestParams(
				sectionTitle = "",
				query = params.query,
				contentTypes = emptyList(),
			)
		}
	}
}