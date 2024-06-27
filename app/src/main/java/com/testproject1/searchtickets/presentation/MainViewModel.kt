package com.testproject1.searchtickets.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testproject1.searchtickets.Offer
import com.testproject1.searchtickets.TAG
import com.testproject1.searchtickets.Ticket
import com.testproject1.searchtickets.TicketOffer
import com.testproject1.searchtickets.data.TicketsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val api: TicketsApi,
): ViewModel() {

    var isLoading by mutableStateOf(false) // todo show circular indicator?
        private set

    var offers by mutableStateOf(emptyList<Offer>())
        private set

    var ticketOffers by mutableStateOf(emptyList<TicketOffer>())
        private set

    var tickets by mutableStateOf(emptyList<Ticket>())
        private set

    var searchDestinationWindowIsVisible by mutableStateOf(false)
        private set

    var hintPage by mutableStateOf<Int?>(null)
        private set

    var departure by mutableStateOf("")
        private set

    var arrival by mutableStateOf("")
        private set

    var departureDate by mutableStateOf<Long?>(null)
        private set

    var arrivalDate by mutableStateOf<Long?>(null)
        private set


    init {
        getOffers() // TODO move to screen, add isLoading
    }

    private fun getOffers() {
        isLoading = true
        viewModelScope.launch {
            try {
                offers = api.getConcertOffers().offers
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
                Log.e(TAG, "getOffers: ", e)
            }
        }
    }

    fun getTicketOffers() {
        isLoading = true
        viewModelScope.launch {
            try {
                ticketOffers = api.getTicketOffers().ticketsOffers
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
                Log.e(TAG, "getTicketOffers: ", e)
            }
        }
    }

    fun getTickets() {
        isLoading = true
        viewModelScope.launch {
            try {
                tickets = api.getTickets().tickets
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
                Log.e(TAG, "getTickets: ", e)
            }
        }
    }

    fun showSearchDestinationWindow(changeTo: Boolean) {
        searchDestinationWindowIsVisible = changeTo
    }

    fun editDeparture(newText: String) {
        departure = newText
    }

    fun editArrival(newText: String) {
        arrival = newText
    }

    fun changeDepartureDate(newDate: Long) {
        departureDate = newDate
    }

    fun changeArrivalDate(newDate: Long?) {
        arrivalDate = newDate
    }

    fun changeHintPage(name: Int?) {
        Log.d(TAG, "changeHintPage: newHintPage = $name, old = $hintPage")
        hintPage = name
    }
}