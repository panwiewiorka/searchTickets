package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.Price
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.TicketOffer
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.Grey1
import com.testproject1.searchtickets.presentation.theme.Grey4
import com.testproject1.searchtickets.presentation.theme.Red
import com.testproject1.searchtickets.presentation.theme.White
import com.testproject1.searchtickets.toFormattedPrice

@Composable
fun RecommendedTickets(
    ticketOffers: List<TicketOffer>
) {
    val offersSize = minOf(ticketOffers.size, 3)
    val selectedOffers = ticketOffers.subList(0, offersSize)
    fun getCircleColor(index: Int): Color {
        return when(index % 3) {
            0 -> Red
            1 -> Blue
            else -> White
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Grey1)
            .padding(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.caption_direct_tickets),
                style = MaterialTheme.typography.titleMedium,
                color = White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(selectedOffers.size) {
            RecommendedTicket(getCircleColor(it), selectedOffers[it].title, selectedOffers[it].price, selectedOffers[it].timeRange)
        }
    }
}

@Composable
fun RecommendedTicket(circleColor: Color, title: String, price: Price, timeRange: List<String>) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(circleColor)
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 2.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.displaySmall,
                        color = White,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = price.value.toFormattedPrice() + " ₽ >",
                        style = MaterialTheme.typography.displaySmall,
                        color = Blue
                    )
                }
                Text(
                    text = timeRange.toString().filterNot { "[]".contains(it) }.replace(',', ' '),
                    style = MaterialTheme.typography.bodyMedium,
                    color = White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Divider(color = Grey4, modifier = Modifier.padding(top = 8.dp))
    }
}