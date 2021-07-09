package com.nsikakthompson.api

import com.google.gson.annotations.SerializedName

data class EventResponse(@SerializedName("_embedded") var _embedded: _Embedded)

data class _Embedded(@SerializedName("events") var events: List<Event>)
data class Event(
    @SerializedName("name") var name: String,
    @SerializedName("id") var id: String,
    @SerializedName("images") var images: List<Images>,
    @SerializedName("sales") var sales: Sales,
    @SerializedName("promoter") var promoter: Promoter,
    @SerializedName("info") var info: String,
    @SerializedName("priceRanges") var priceRanges: List<PriceRanges>,
    @SerializedName("_embedded") var embedded:Embedded
)

data class Images(@SerializedName("url") var url: String)
data class Sales(@SerializedName("public") var public: Public)
data class Public(
    @SerializedName("startDateTime") var startDateTime: String,
    @SerializedName("endDateTime") var endDateTime: String
)

data class Promoter(
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String
)

data class PriceRanges(
    @SerializedName("currency") var currency: String,
    @SerializedName("min") var min: Double
)
data class Embedded(@SerializedName("venues") var venues: List<Venues>)
data class Venues(@SerializedName("name") var name: String,
@SerializedName("state") var state: State)
data class State(@SerializedName("name") var name: String)