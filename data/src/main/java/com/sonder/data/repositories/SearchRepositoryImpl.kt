package com.sonder.data.repositories

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {

    override val searchQuery: Flow<String>
        get() = TODO("Not yet implemented")

    override fun clearSectionSearchResults() {
        TODO("Not yet implemented")
    }
}