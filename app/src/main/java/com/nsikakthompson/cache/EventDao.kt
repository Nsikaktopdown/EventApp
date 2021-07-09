package com.nsikakthompson.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.nsikakthompson.api.Event
import androidx.paging.DataSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nsikakthompson.data.Result

@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getEvents(): LiveData<List<EventEntity>>

    @Query("SELECT * FROM event")
    fun getPagedEvents(): DataSource.Factory<Int, EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<EventEntity>)

    @Query("UPDATE event SET isWish = :isWish WHERE id = :event_id ")
    suspend fun updateIsWish(isWish: Boolean, event_id: String)

}