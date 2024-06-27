package com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.Grey1
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun FromTo4(
    departure: String,
    arrival: String,
    departureDate: Long,
    goBack: () -> Unit,
) {
    val formatter = SimpleDateFormat("dd MMMM", Locale.getDefault())
    val formattedDate = formatter.format(departureDate).lowercase()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Grey1)
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = stringResource(R.string.button_description_arrow),
            tint = Blue,
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = goBack)
                .padding(8.dp)
        )

        Column {
            Text(
                text = "$departure - $arrival",
                style = MaterialTheme.typography.titleSmall,
                color = White,
                modifier = Modifier.padding(2.dp)
            )

            Text(
                text = "$formattedDate, 1 пассажир",
                style = MaterialTheme.typography.displaySmall,
                color = Grey6,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}