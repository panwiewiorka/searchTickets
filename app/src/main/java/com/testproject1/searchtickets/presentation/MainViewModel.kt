package com.testproject1.searchtickets.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testproject1.searchtickets.TAG
import com.testproject1.searchtickets.data.AppDao
import com.testproject1.searchtickets.data.AppData
import com.testproject1.searchtickets.data.TicketsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val api: TicketsApi,
    private val dao: AppDao,
): ViewModel() {

    private val _appState = MutableStateFlow(AppState())
    val appState: StateFlow<AppState> = _appState.asStateFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            val appData = AppData()
            dao.populateSettings(appData)
            
            _appState.update { it.copy(
                departure = dao.loadSettings().departure
            ) }
        }
    }

    fun saveToDb() {
        viewModelScope.launch {
            dao.saveSettings(
                AppData(departure = appState.value.departure)
            )
        }
    }
    
    private fun showLoading(show: Boolean) {
        _appState.update { it.copy(
            isLoading = show    
        ) }
    }

    fun getOffers() {
        showLoading(true)
        viewModelScope.launch {
            try {
                _appState.update { it.copy(
                    offers = api.getConcertOffers().offers
                ) }
                showLoading(false)
            } catch (e: Exception) {
                showLoading(false)
                Log.e(TAG, "getOffers: ", e)
            }
        }
    }

    fun getTicketOffers() {
        showLoading(true)
        viewModelScope.launch {
            try {
                _appState.update { it.copy(
                    ticketOffers = api.getTicketOffers().ticketsOffers
                ) }
                showLoading(false)
            } catch (e: Exception) {
                showLoading(false)
                Log.e(TAG, "getTicketOffers: ", e)
            }
        }
    }

    fun getTickets() {
        showLoading(true)
        viewModelScope.launch {
            try {
                _appState.update { it.copy(
                    tickets = api.getTickets().tickets
                ) }
                showLoading(false)
            } catch (e: Exception) {
                showLoading(false)
                Log.e(TAG, "getTickets: ", e)
            }
        }
    }

    fun showSearchDestinationWindow(changeTo: Boolean) {
        _appState.update { it.copy(
            searchDestinationWindowIsVisible = changeTo
        ) }
    }

    fun editDeparture(newText: String) {
        _appState.update { it.copy(
            departure = newText
        ) }
    }

    fun editArrival(newText: String) {
        _appState.update { it.copy(
            arrival = newText
        ) }
    }

    fun changeDepartureDate(newDate: Long) {
        _appState.update { it.copy(
            departureDate = newDate
        ) }
    }

    fun changeArrivalDate(newDate: Long?) {
        _appState.update { it.copy(
            arrivalDate = newDate
        ) }
    }

    fun changeHintPage(name: Int?) {
        _appState.update { it.copy(
            hintPage = name
        ) }
    }
}