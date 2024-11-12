package com.sonder.domain.repositories

import kotlinx.coroutines.flow.Flow

// TODO Implement SearchRepository. Change as needed.
interface SearchRepository {
    val searchQuery: Flow<String>
    fun clearSectionSearchResults()
}