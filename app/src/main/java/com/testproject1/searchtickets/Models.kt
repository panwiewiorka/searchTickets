package com.testproject1.searchtickets

import com.google.gson.annotations.SerializedName

data class Price(
    val value: Int,
)

data class Offers(
    val offers: List<Offer>
)

data class Offer(
    val id: Int,
    val title: String,
    val town: String,
    val price: Price,
)

data class TicketOffers(
    @SerializedName(value = "tickets_offers")
    val ticketsOffers: List<TicketOffer>
)

data class TicketOffer(
    val id: Int,
    val title: String,
    @SerializedName(value = "time_range")
    val timeRange: List<String>,
    val price: Price
)

data class Tickets(
    val tickets: List<Ticket>
)

data class Ticket (
    val id: Long,
    val badge: String?,
    val price: Price,
    @SerializedName(value = "provider_name")
    val providerName: String,
    val company: String,
    val departure: Arrival,
    val arrival: Arrival,
    @SerializedName(value = "has_transfer")
    val hasTransfer: Boolean,
    @SerializedName(value = "has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    @SerializedName(value = "hand_luggage")
    val handLuggage: HandLuggage,
    @SerializedName(value = "is_returnable")
    val isReturnable: Boolean,
    @SerializedName(value = "is_exchangable")
    val isExchangable: Boolean
)

data class Arrival (
    val town: String,
    val date: String,
    val airport: String
)

data class HandLuggage (
    @SerializedName(value = "has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String
)

data class Luggage (
    @SerializedName(value = "has_luggage")
    val hasLuggage: Boolean,
    val price: Price
)