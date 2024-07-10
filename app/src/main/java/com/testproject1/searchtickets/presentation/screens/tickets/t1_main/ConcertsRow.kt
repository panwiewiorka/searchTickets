package com.testproject1.searchtickets.presentation.screens.tickets.t1_main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.Offer
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.White
import com.testproject1.searchtickets.toFormattedPrice

@Composable
fun ConcertsRow(
    offers: List<Offer>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(50.dp)
    ) {
        items(offers.size) {
            ConcertCard(offers[it])
        }
    }
}


@Composable
fun ConcertCard(
    offer: Offer
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val image = when (offer.id) {
            1 -> R.drawable.concert1
            2 -> R.drawable.concert2
            else -> R.drawable.concert3
        }

        Image(
            painterResource(image),
            contentDescription = null,
            modifier = Modifier
                .size(132.dp)
                .aspectRatio(1f / 1f)
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = offer.title,
            style = MaterialTheme.typography.titleSmall,
            color = White,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Text(
            text = offer.town,
            style = MaterialTheme.typography.bodyMedium,
            color = White,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painterResource(R.drawable.airplane), contentDescription = null, tint = Grey6)

            Text(
                text = stringResource(R.string.price_from) + offer.price.value.toFormattedPrice() + stringResource(R.string.currency),
                style = MaterialTheme.typography.bodyMedium,
                color = White,
            )
        }
    }
}