package com.nsikakthompson.presentation.compose.data

import com.nsikakthompson.cache.EventEntity

class FakeEventProvider {

    companion object {
        val getEvent: EventEntity
            get() = EventEntity(
                id = "",
                name = "Tampa Bay Buccaneers",
                imageUrl = "",
                startDateTime = "2021-09-10T00:20:00Z",
                endDateTime = "2021-09-10T00:20:00Z",
                promoterName = "NFL REGULAR SEASON",
                promoterDesc = "NFL REGULAR SEASON / NTL / USA",
                price = 40.0,
                currency = "",
                ticketType = "Ball",
                venueName = "Ball Arena",
                venueState = "California",
                openHoursDetail = "2021-09-10T00:20:00Z",
                acceptedPaymentDetail = "",
                willCallDetail = "",
                isWish = false
            )
    }
}