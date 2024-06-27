package com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun BottomButtons(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(16.dp)
            .clip(CircleShape)
            .background(Blue)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {  }
        ) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = stringResource(R.string.button_text_filter),
                style = MaterialTheme.typography.bodyMedium,
                color = White,
                modifier = Modifier.padding(end = 16.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(CircleShape)
                .clickable {  }
        ) {
            Icon(
                painter = painterResource(R.drawable.schedule),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )

            Text(
                text = stringResource(R.string.button_text_graph),
                style = MaterialTheme.typography.displaySmall,
                color = White,
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}