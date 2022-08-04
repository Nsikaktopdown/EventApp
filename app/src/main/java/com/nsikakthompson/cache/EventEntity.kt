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
    var promoterName: String? = null,
    var promoterDesc: String? = null,
    var price: Double,
    var currency: String,
    var ticketType: String,
    var venueName: String,
    var venueState: String,
    var openHoursDetail: String,
    var acceptedPaymentDetail: String,
    var willCallDetail: String,
    var isWish: Boolean

): Parcelable


