package com.nsikakthompson.data

import com.nsikakthompson.cache.EventEntity

interface EventRepository {
    suspend fun addToWishList(eventEntity: EventEntity)
    suspend fun removeWishList(eventEntity: EventEntity)
    suspend fun getEventIsWished(event_id: String): Boolean
    suspend fun getCount(): Int
}