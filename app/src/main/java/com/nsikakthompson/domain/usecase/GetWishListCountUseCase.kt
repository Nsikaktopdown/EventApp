package com.nsikakthompson.domain.usecase

import com.nsikakthompson.domain.EventRepository

class GetWishListCountUseCase(
    private val eventRepository: EventRepository
) {
    suspend fun call(): Int = eventRepository.getCount()

}