package com.testproject1.searchtickets.presentation.screens.tickets.t1_main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.filterCyrillic
import com.testproject1.searchtickets.presentation.theme.Grey2
import com.testproject1.searchtickets.presentation.theme.Grey4
import com.testproject1.searchtickets.presentation.theme.Grey5
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun FromTo1(
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    showSearchDestinationWindow: (Boolean) -> Unit,
) {
    var departureIsFocused by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .background(Grey2, RoundedCornerShape(24.dp))
            .padding(20.dp)
            .offset(0.dp, 4.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .offset(0.dp, (-4).dp)
            .background(Grey4, RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.button_description_search),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(
                        enabled = arrival.isNotBlank() && departure.isNotBlank(),
                        onClick = { }
                    )
                    .padding(8.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = departure,
                    onValueChange = { editDeparture(it.filterCyrillic(context)) },
                    singleLine = true,
                    cursorBrush = SolidColor(White),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { showSearchDestinationWindow(true) }),
                    decorationBox = { innerTextField ->
                        if(departure.isEmpty() && !departureIsFocused) {
                            Text(
                                text = stringResource(R.string.field_label_from),
                                style = MaterialTheme.typography.titleSmall,
                                color = Grey6
                            )
                        }
                        innerTextField()
                    },
                    textStyle = MaterialTheme.typography.titleSmall.copy(color = White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .onFocusChanged { departureIsFocused = it.isFocused }
                )

                Divider(color = Grey5)

                Text(
                    text = stringResource(R.string.field_label_to),
                    style = MaterialTheme.typography.titleSmall,
                    color = Grey6,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { showSearchDestinationWindow(true) }
                        )
                )

                /*
                BasicTextField(
                    value = arrival,
                    onValueChange = { arrival = it.filterCyrillic(context) },
                    singleLine = true,
                    cursorBrush = SolidColor(White),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    decorationBox = { innerTextField ->
                        if(arrival.isEmpty() && !arrivalIsFocused) {
                            Text(
                                text = stringResource(R.string.field_label_to),
                                style = MaterialTheme.typography.titleSmall,
                                color = Grey6
                            )
                        }
                        innerTextField()
                    },
                    textStyle = MaterialTheme.typography.titleSmall.copy(color = White),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .focusRequester(focusRequester)
                        .onFocusChanged { arrivalIsFocused = it.isFocused }
                )
                 */
            }
        }
    }
}