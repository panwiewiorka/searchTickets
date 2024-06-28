package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.presentation.AppState
import com.testproject1.searchtickets.presentation.theme.DarkBlue

@Composable
fun ArrivalChosenScreen(
    state: AppState,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    changeDepartureDate: (Long) -> Unit,
    changeArrivalDate: (Long?) -> Unit,
    getTicketOffers: () -> Unit,
    saveDepartureToDb: () -> Unit,
    goBack: () -> Unit,
    goToShowAllTicketsScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
//        if (ticketOffers.isEmpty())
        getTicketOffers()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() }
            )
            .padding(vertical = 32.dp)
    ) {
        FromTo3(state.departure, state.arrival, editDeparture, editArrival, goBack)

        SearchOptions(state.departureDate, state.arrivalDate, changeDepartureDate, changeArrivalDate)

        if (state.isLoading && state.ticketOffers.isEmpty()) {
            CircularProgressIndicator(
                color = DarkBlue,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            RecommendedTickets(state.ticketOffers)
        }

        Spacer(modifier = Modifier.weight(1f))

        ShowAllTicketsButton(state.departure, state.arrival, state.departureDate) {
            saveDepartureToDb()
            goToShowAllTicketsScreen()
        }

        SubscribeSwitch()
    }
}