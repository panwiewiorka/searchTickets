package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.Grey1
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun SubscribeSwitch() {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(R.drawable.bell),
                contentDescription = null,
                tint = Blue,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.switch_text_subscribe),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )
            var isChecked by remember { mutableStateOf(false) }
            Switch(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = White,
                    checkedThumbColor = Blue,
                    uncheckedTrackColor = White,
                    checkedTrackColor = Blue
                )
            )
        }
    }
}