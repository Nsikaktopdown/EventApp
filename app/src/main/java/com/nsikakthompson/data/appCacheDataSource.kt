package com.nsikakthompson.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nsikakthompson.cache.EventDao
import com.nsikakthompson.cache.EventEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class appCacheDataSource(
    private var dao: EventDao,
    private val scope: CoroutineScope
) {
    private var eventLiveData = MutableLiveData<EventEntity>()

//    fun getEventById(event_id: String) : LiveData<EventEntity> {
//
//        scope.launch {
//            //eventLiveData.postValue( dao.getEventById(event_id))
//        }
//    }

}