package com.testproject1.searchtickets.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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

@Composable
fun EmptyScreen(
    title: Int,
    goBack: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (goBack != null) {
            Button(
                onClick = { goBack() },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .clip(CircleShape)
                    .padding(end = 16.dp)
            ) {
                Icon(painterResource(R.drawable.arrow), stringResource(title))
            }
        }

        Text(text = stringResource(title), style = MaterialTheme.typography.titleLarge)
    }
}