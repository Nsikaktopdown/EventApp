package com.nsikakthompson.domain

import com.nsikakthompson.cache.EventEntity

interface EventRepository {
    suspend fun addToWishList(eventEntity: EventEntity)
    suspend fun removeWishList(eventEntity: EventEntity)
    suspend fun getEventById(event_id: String): EventEntity
    suspend fun getCount(): Int
}