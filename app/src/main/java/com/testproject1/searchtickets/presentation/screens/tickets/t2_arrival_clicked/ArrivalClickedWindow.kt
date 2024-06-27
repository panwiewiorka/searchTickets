package com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.presentation.composables.EmptyScreen
import com.testproject1.searchtickets.presentation.theme.Grey1

@Composable
fun ArrivalClickedWindow(
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    hintPage: Int?,
    changeHintPage: (Int?) -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    BackHandler(
        enabled = hintPage != null
    ) {
        changeHintPage(null)
    }

    if (hintPage != null) {
        EmptyScreen(hintPage) { changeHintPage(null) }
    } else {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Grey1)
                .clickable( // FIXME not working
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { focusManager.clearFocus() }
                )
                .padding(16.dp)
        ) {
            FromTo2(departure, arrival, editDeparture, editArrival, goToArrivalChosenScreen)
            Hints(departure, editArrival, changeHintPage, goToArrivalChosenScreen)
            Destinations(departure, editArrival, goToArrivalChosenScreen)
        }
    }
}