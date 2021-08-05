package com.nsikakthompson.domain.usecase

interface SuspendUseCase<I, O> {
    suspend fun call(input: I): O
}
interface SuspendUseCaseNoParams<O> {
    suspend fun call(): O
}