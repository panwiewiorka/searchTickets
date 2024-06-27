package com.testproject1.searchtickets.presentation

import com.testproject1.searchtickets.Offer
import com.testproject1.searchtickets.Ticket
import com.testproject1.searchtickets.TicketOffer

data class AppState(
    val isLoading: Boolean = false,
    val offers: List<Offer> = emptyList(),
    val ticketOffers: List<TicketOffer> = emptyList(),
    val tickets: List<Ticket> = emptyList(),
    val searchDestinationWindowIsVisible: Boolean = false,
    var hintPage: Int? = null,
    var departure: String = "",
    var arrival: String = "",
    var departureDate: Long? = null,
    var arrivalDate: Long? = null,
)