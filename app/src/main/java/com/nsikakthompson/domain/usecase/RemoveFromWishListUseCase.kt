package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository

class RemoveFromWishListUseCase(
    private val eventRepository: EventRepository
) {
    suspend fun call(input: EventEntity) = eventRepository.removeWishList(input)


}