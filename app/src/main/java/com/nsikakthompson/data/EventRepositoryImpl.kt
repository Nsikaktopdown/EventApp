package com.nsikakthompson.data

import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import com.nsikakthompson.domain.EventRepository

class EventRepositoryImpl(
    private var dao: EventDao,
) : EventRepository {

    override suspend fun addToWishList(eventEntity: EventEntity) {
        return dao.insert(eventEntity)
    }

    override suspend fun removeWishList(eventEntity: EventEntity) {
        dao.delete(eventEntity)
    }

    override suspend fun getEventById(event_id: String): EventEntity {
        return dao.getEventById(event_id)
    }

    override suspend fun getCount(): Int {
        return dao.getCount()
    }


}

object Empty