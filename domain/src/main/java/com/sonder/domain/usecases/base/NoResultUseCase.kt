package com.sonder.domain.usecases.base

abstract class NoResultUseCase<in PARAMS> : UseCase<PARAMS, Unit>()