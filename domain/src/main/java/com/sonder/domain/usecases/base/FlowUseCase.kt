package com.sonder.domain.usecases.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in PARAMS, RESULT>() {

    fun execute(params: PARAMS): Flow<RESULT> {
        return construct(params)
    }

    protected abstract fun construct(params: PARAMS): Flow<RESULT>

}