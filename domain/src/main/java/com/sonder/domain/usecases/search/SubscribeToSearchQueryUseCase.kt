package com.sonder.domain.usecases.search

import com.sonder.domain.repositories.SearchRepository
import com.sonder.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubscribeToSearchQueryUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) : FlowUseCase<Unit, String>() {

    override fun construct(params: Unit): Flow<String> {
        return searchRepository.searchQuery
    }
}