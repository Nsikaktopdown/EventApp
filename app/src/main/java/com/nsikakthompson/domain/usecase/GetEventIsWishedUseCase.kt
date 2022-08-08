package com.nsikakthompson.domain.usecase

import com.nsikakthompson.data.EventRepository

class GetEventIsWishedUseCase(
    private val eventRepository: EventRepository
) {
    suspend fun call(input: String): Boolean = eventRepository.getEventIsWished(input)

}