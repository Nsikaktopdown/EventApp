package com.nsikakthompson.domain

data class EventDomain(
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
)
