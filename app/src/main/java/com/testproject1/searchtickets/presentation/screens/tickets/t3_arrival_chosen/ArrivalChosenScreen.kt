package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.TicketOffer

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArrivalChosenScreen(
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    departureDate: Long?,
    arrivalDate: Long?,
    changeDepartureDate: (Long) -> Unit,
    changeArrivalDate: (Long?) -> Unit,
    ticketOffers: List<TicketOffer>?,
    getTicketOffers: () -> Unit,
    goBack: () -> Unit,
    goToShowAllTicketsScreen: () -> Unit,
) {
    LaunchedEffect(Unit) {
//        if (ticketOffers.isEmpty())
        getTicketOffers()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp)
    ) {
        FromTo3(departure, arrival, editDeparture, editArrival, goBack)
        SearchOptions(departureDate, arrivalDate, changeDepartureDate, changeArrivalDate)
        ticketOffers?.let { RecommendedTickets(ticketOffers) }
        Spacer(modifier = Modifier.weight(1f))
        ShowAllTicketsButton(departureDate, goToShowAllTicketsScreen)
        SubscribeSwitch()
    }
}