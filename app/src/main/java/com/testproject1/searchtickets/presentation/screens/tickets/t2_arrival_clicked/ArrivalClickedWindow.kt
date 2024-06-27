package com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.presentation.composables.EmptyScreen
import com.testproject1.searchtickets.presentation.theme.Black
import com.testproject1.searchtickets.presentation.theme.Grey1

@Composable
fun ArrivalClickedWindow(
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    hintPage: Int?,
    changeHintPage: (Int?) -> Unit,
    showSearchDestinationWindow: (Boolean) -> Unit,
    saveDepartureToDb: () -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BackHandler {
        if (hintPage != null) changeHintPage(null) else showSearchDestinationWindow(false)
    }

    if (hintPage != null) {
        EmptyScreen(hintPage) { changeHintPage(null) }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.4f))
                .clickable { showSearchDestinationWindow(false) }
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Grey1)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { focusManager.clearFocus() }
                    )
                    .padding(16.dp)
            ) {
                FromTo2(departure, arrival, editDeparture, editArrival, saveDepartureToDb, goToArrivalChosenScreen)
                Hints(departure, editArrival, changeHintPage, saveDepartureToDb, goToArrivalChosenScreen)
                Destinations(departure, editArrival, saveDepartureToDb, goToArrivalChosenScreen)
            }
        }
    }
}