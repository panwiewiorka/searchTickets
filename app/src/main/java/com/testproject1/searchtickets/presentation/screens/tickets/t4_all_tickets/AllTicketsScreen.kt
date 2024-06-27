package com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.presentation.AppState
import com.testproject1.searchtickets.presentation.theme.DarkBlue

@Composable
fun AllTicketsScreen(
    state: AppState,
    getTickets: () -> Unit,
    goBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        getTickets()
    }

    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            FromTo4(state.departure, state.arrival, state.departureDate!!, goBack)

            if (state.isLoading) {
                CircularProgressIndicator(color = DarkBlue)
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    items(state.tickets.size) {
                        TicketCard(state.tickets[it])
                    }

                }
            }
        }

        BottomButtons(modifier = Modifier.align(Alignment.BottomCenter))
    }
}