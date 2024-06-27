package com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.UiText
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.DarkBlue
import com.testproject1.searchtickets.presentation.theme.Green
import com.testproject1.searchtickets.presentation.theme.Red
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun Hints(
    departure: String,
    editArrival: (String) -> Unit,
    changeHintPage: (Int?) -> Unit,
    saveDepartureToDb: () -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Hint(R.string.hint_route, R.drawable.route, Modifier.weight(1f), Green) {
            changeHintPage(R.string.hint_route)
        }
        Hint(R.string.hint_destination, R.drawable.ball1, Modifier.weight(1f), Blue) {
            editArrival(UiText.StringResourse(R.string.hint_destination).asString(context))
            if (departure.isNotBlank()) {
                saveDepartureToDb()
                goToArrivalChosenScreen()
            }
        }
        Hint(R.string.hint_weekend, R.drawable.calendar, Modifier.weight(1f), DarkBlue) {
            changeHintPage(R.string.hint_weekend)
        }
        Hint(R.string.hint_hot_tickets, R.drawable.fire, Modifier.weight(1f), Red) {
            changeHintPage(R.string.hint_hot_tickets)
        }
    }
}

@Composable
fun Hint(text: Int, icon: Int, modifier: Modifier, bgColor: Color, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
    ) {
        Icon(
            painterResource(icon),
            contentDescription = null,
            tint = White,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(bgColor)
                .padding(12.dp)
            )

        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodyMedium,
            color = White,
            textAlign = TextAlign.Center
        )
    }
}