package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun ShowAllTicketsButton(
    departure: String,
    arrival: String,
    departureDate: Long?,
    onClick: () -> Unit,
) {
    Button(
        enabled = departureDate != null && departure.isNotBlank() && arrival.isNotBlank(),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Blue),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.button_text_all_tickets),
            style = MaterialTheme.typography.labelLarge,
            color = White
        )
    }
}