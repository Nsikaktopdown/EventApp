package com.nsikakthompson.presentation.compose.data

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.nsikakthompson.cache.EventEntity

class EventType : NavType<EventEntity>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): EventEntity? {
       return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): EventEntity {
       return Gson().fromJson(value, EventEntity::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: EventEntity) {
       bundle.putParcelable(key, value)
    }

}