package com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.Ticket
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.Grey1
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.Red
import com.testproject1.searchtickets.presentation.theme.White
import com.testproject1.searchtickets.toFormattedPrice
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Composable
fun TicketCard(
    ticket: Ticket
) {
    var tagHeight by remember { mutableStateOf(0.dp) }

    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val departureDateTime = LocalDateTime.parse(ticket.departure.date, formatter)
    val arrivalDateTime = LocalDateTime.parse(ticket.arrival.date, formatter)
    val departureMillis = departureDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
    val arrivalMillis = arrivalDateTime.toInstant(ZoneOffset.UTC).toEpochMilli()
    val difference = ((arrivalMillis - departureMillis) / 1000f / 3600 * 2 + 0.5).toInt() / 2f

    Box(
        modifier = Modifier.padding(top = tagHeight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(Grey1)
                .padding(16.dp)
        ) {
            Text(
                text = ticket.price.value.toFormattedPrice() + stringResource(R.string.currency),
                style = MaterialTheme.typography.titleLarge,
                color = White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Red)
                )

                Column {
                    val text = buildAnnotatedString {
                        append(ticket.departure.date.substringAfter('T').substringBeforeLast(':'))
                        withStyle(style = SpanStyle(Grey6)) { append(" — ") }
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.displaySmall,
                        color = White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = ticket.departure.airport,
                        style = MaterialTheme.typography.displaySmall,
                        color = Grey6
                    )
                }

                Column {
                    Text(
                        text = ticket.arrival.date.substringAfter('T').substringBeforeLast(':'),
                        style = MaterialTheme.typography.displaySmall,
                        color = White
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = ticket.arrival.airport,
                        style = MaterialTheme.typography.displaySmall,
                        color = Grey6
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                val text = buildAnnotatedString {
                    append(stringResource(R.string.hours, difference.toString().replace(".0", "")))
                    if (ticket.hasTransfer) {
                        withStyle(style = SpanStyle(Grey6)) { append(" / ") }
                        append(stringResource(R.string.no_transfers))
                    }
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )
            }
        }

        if (!ticket.badge.isNullOrBlank()) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)
                        layout(placeable.width, placeable.height) {
                            placeable.place(0, placeable.height / -2)
                            tagHeight = placeable.height.toDp() / 2
                        }
                    }
                    .clip(CircleShape)
                    .background(Blue)
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = ticket.badge,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}