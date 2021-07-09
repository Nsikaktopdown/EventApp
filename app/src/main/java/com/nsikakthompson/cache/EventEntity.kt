package com.nsikakthompson.cache

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "event")
data class EventEntity(
    @PrimaryKey
    var id: String,
    var name: String,
    var imageUrl: String,
    var startDateTime: String,
    var endDateTime: String,
    var promoterName: String,
    var promoterDesc: String,
    var price: Double,
    var currency: String,
    var venueName: String,
    var venueState: String,
    var isWish: Boolean

): Parcelable
