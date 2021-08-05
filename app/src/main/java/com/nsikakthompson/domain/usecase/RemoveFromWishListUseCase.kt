package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class RemoveFromWishListUseCase (
    private val eventRepository: EventRepository,
    private val dispatcherProvider: DispatcherProvider
) : SuspendUseCase<EventEntity, Any> {
    override suspend fun call(input: EventEntity): Any {
        withContext(dispatcherProvider.getIO()) {
            eventRepository.removeWishList(input)
        }
        return Any()
    }

}