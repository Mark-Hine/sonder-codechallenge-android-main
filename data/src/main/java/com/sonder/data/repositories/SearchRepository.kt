package com.sonder.data.repositories

import com.sonder.data.models.SearchRequestParams
import com.sonder.data.models.SearchSectionResult
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    val searchQuery: Flow<String>
    fun clearSectionSearchResults()
    fun getSectionSearchResults(params: SearchRequestParams): Flow<SearchSectionResult>
}