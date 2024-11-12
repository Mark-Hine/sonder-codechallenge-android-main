package com.sonder.domain.usecases.base

abstract class UseCase<in PARAMS, RESULT> internal constructor() {

    suspend fun execute(params: PARAMS): RESULT {
        return construct(params)
    }

    protected abstract suspend fun construct(params: PARAMS): RESULT

}