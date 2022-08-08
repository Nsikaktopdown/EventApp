package com.nsikakthompson.data

import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity

class EventRepositoryImpl(
    private var dao: EventDao,
) : EventRepository {

    override suspend fun addToWishList(eventEntity: EventEntity) {
        return dao.insert(eventEntity)
    }

    override suspend fun removeWishList(eventEntity: EventEntity) {
        dao.delete(eventEntity)
    }

    override suspend fun getEventIsWished(event_id: String): Boolean{
        return dao.isWished(event_id)
    }

    override suspend fun getCount(): Int {
        return dao.getCount()
    }


}

object Empty