package com.testproject1.searchtickets.presentation.screens.tickets.t2_arrival_clicked

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.UiText
import com.testproject1.searchtickets.presentation.theme.Grey2
import com.testproject1.searchtickets.presentation.theme.Grey4
import com.testproject1.searchtickets.presentation.theme.Grey5
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun Destinations(
    departure: String,
    editArrival: (String) -> Unit,
    goToArrivalChosenScreen: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Grey2)
            .padding(16.dp)
    ) {
        Destination(R.drawable.destination1, R.string.destination_istanbul) {
            editArrival(UiText.StringResourse(R.string.destination_istanbul).asString(context))
            if (departure.isNotBlank()) goToArrivalChosenScreen()
        }
        Destination(R.drawable.destination2, R.string.destination_sochi) {
            editArrival(UiText.StringResourse(R.string.destination_sochi).asString(context))
            if (departure.isNotBlank()) goToArrivalChosenScreen()
        }
        Destination(R.drawable.destination3, R.string.destination_phuket) {
            editArrival(UiText.StringResourse(R.string.destination_phuket).asString(context))
            if (departure.isNotBlank()) goToArrivalChosenScreen()
        }
    }
}

@Composable
fun Destination(image: Int, title: Int, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(top = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = stringResource(title),
                    style = MaterialTheme.typography.titleSmall,
                    color = White,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.destination_description),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Grey5
                )
            }
        }
        Divider(color = Grey4, modifier = Modifier.padding(top = 8.dp))
    }
}