package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class GetEventByIdUseCase(
    private val eventRepository: EventRepository,
    private val dispatcherProvider: DispatcherProvider
) : SuspendUseCase<String, EventEntity> {
    override suspend fun call(input: String): EventEntity {
        return withContext(dispatcherProvider.getIO()) {
            eventRepository.getEventById(input)
        }

    }

}