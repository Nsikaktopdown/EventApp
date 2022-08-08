package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.data.EventRepository

class AddToWishListUseCase(
    private val eventRepository: EventRepository,
) {
    suspend fun call(input: EventEntity) = eventRepository.addToWishList(input)

}