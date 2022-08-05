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
                openHoursDetail = "Stadium Office Number: Tampa Bay Bucaneers: (813)879-2827 Buccaneer Groups Sales:(813)870-2700 (ask for Group Sales) Stadium Jumping Inc: (813)253-2782 USF Football: 1-800-GOBULLS Outback Bowl: (813)874-2695",
                acceptedPaymentDetail = "",
                willCallDetail = "",
                isWish = false
            )
    }
}