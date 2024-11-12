package com.sonder.domain.usecases.search

import com.sonder.domain.repositories.SearchRepository
import com.sonder.domain.usecases.base.NoResultUseCase
import javax.inject.Inject

class ClearSearchResultsUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : NoResultUseCase<Unit>() {

    override suspend fun construct(params: Unit) {
        searchRepository.clearSectionSearchResults()
    }
}