package com.sonder.data.repositories

import com.sonder.data.models.SearchRequestParams
import com.sonder.data.models.SearchSectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {

    private val _searchQuery = MutableStateFlow("")
    override val searchQuery: Flow<String> = _searchQuery.asStateFlow()

    override fun clearSectionSearchResults() {
        TODO("Not yet implemented")
    }
}