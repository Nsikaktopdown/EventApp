package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class GetWishListCountUseCase(
    private val eventRepository: EventRepository,
    private val dispatcherProvider: DispatcherProvider
) : SuspendUseCaseNoParams<Int> {
    override suspend fun call(): Int {
        return withContext(dispatcherProvider.getIO()) {
            eventRepository.getCount()
        }

    }

}