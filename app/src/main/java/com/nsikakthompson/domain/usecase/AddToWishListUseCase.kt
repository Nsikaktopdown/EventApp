package com.nsikakthompson.domain.usecase

import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository
import com.nsikakthompson.utils.DispatcherProvider
import kotlinx.coroutines.withContext

class AddToWishListUseCase(
    private val eventRepository: EventRepository,
) {
    suspend fun call(input: EventEntity) = eventRepository.addToWishList(input)

}