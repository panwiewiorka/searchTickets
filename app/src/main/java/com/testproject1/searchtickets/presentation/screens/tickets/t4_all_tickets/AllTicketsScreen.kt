package com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.Ticket

@Composable
fun AllTicketsScreen(
    departure: String,
    arrival: String,
    departureDate: Long,
    tickets: List<Ticket>,
    getTickets: () -> Unit,
    goBack: () -> Unit,
) {
    LaunchedEffect(Unit) {
        getTickets()
    }

    Box {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            FromTo4(departure, arrival, departureDate, goBack)

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(tickets.size) {
                    TicketCard(tickets[it])
                }
            }
        }

        BottomButtons(modifier = Modifier.align(Alignment.BottomCenter))
    }
}