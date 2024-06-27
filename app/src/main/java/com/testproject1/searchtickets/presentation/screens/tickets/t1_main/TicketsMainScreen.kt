package com.testproject1.searchtickets.presentation.screens.tickets.t1_main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.testproject1.searchtickets.Offer
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked.ArrivalClickedWindow
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun TicketsMainScreen(
    offers: List<Offer>,
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    searchDestinationWindowIsVisible: Boolean,
    showSearchDestinationWindow: (Boolean) -> Unit,
    hintPage: Int?,
    changeHintPage: (Int?) -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() }
            )
            .padding(horizontal = 16.dp)
            .blur(if (searchDestinationWindowIsVisible) 10.dp else 0.dp)
    ) {
        Text(
            text = stringResource(R.string.caption_search_tickets),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = White,
            modifier = Modifier.padding(top = 32.dp)
        )

        FromTo1(departure, arrival, editDeparture, showSearchDestinationWindow)

        Text(
            text = stringResource(R.string.caption_music),
            style = MaterialTheme.typography.titleLarge,
            color = White,
            modifier = Modifier.align(Alignment.Start)
        )

        ConcertsRow(offers)
    }

    if (searchDestinationWindowIsVisible) {
        Dialog(
            onDismissRequest = { showSearchDestinationWindow(false) },
            properties = DialogProperties(
                usePlatformDefaultWidth = false
            )
        ) {
            ArrivalClickedWindow(
                departure,
                arrival,
                editDeparture,
                editArrival,
                hintPage,
                changeHintPage,
                goToArrivalChosenScreen
            )
        }
    }
}