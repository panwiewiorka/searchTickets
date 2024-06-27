package com.testproject1.searchtickets.presentation.screens.tickets.t1_main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.AppState
import com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked.ArrivalClickedWindow
import com.testproject1.searchtickets.presentation.theme.DarkBlue
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun TicketsMainScreen(
    state: AppState,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    showSearchDestinationWindow: (Boolean) -> Unit,
    getOffers: () -> Unit,
    changeHintPage: (Int?) -> Unit,
    saveDepartureToDb: () -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val animatedBlur by animateDpAsState(if (state.searchDestinationWindowIsVisible) 10.dp else 0.dp)

    LaunchedEffect(Unit) {
        getOffers()
    }

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
            .blur(animatedBlur)
    ) {
        Text(
            text = stringResource(R.string.caption_search_tickets),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = White,
            modifier = Modifier.padding(top = 32.dp)
        )

        FromTo1(state.departure, state.arrival, editDeparture, saveDepartureToDb, showSearchDestinationWindow)

        Text(
            text = stringResource(R.string.caption_music),
            style = MaterialTheme.typography.titleLarge,
            color = White,
            modifier = Modifier.align(Alignment.Start)
        )

        if (state.isLoading) CircularProgressIndicator(color = DarkBlue) else ConcertsRow(state.offers)
    }

    AnimatedContent(targetState = state.searchDestinationWindowIsVisible) {
        if (it) {
            ArrivalClickedWindow(
                state.departure,
                state.arrival,
                editDeparture,
                editArrival,
                state.hintPage,
                changeHintPage,
                showSearchDestinationWindow,
                saveDepartureToDb,
                goToArrivalChosenScreen
            )
        }
    }
}