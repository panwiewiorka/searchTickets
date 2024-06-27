package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.filterCyrillic
import com.testproject1.searchtickets.presentation.theme.Grey2
import com.testproject1.searchtickets.presentation.theme.Grey4
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.White

@Composable
fun FromTo3(
    departure: String,
    arrival: String,
    editDeparture: (String) -> Unit,
    editArrival: (String) -> Unit,
    goBack: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    var departureIsFocused by rememberSaveable { mutableStateOf(false) }
    var arrivalIsFocused by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .offset(0.dp, 4.dp)
            .shadow(4.dp, RoundedCornerShape(16.dp))
            .offset(0.dp, (-4).dp)
            .background(Grey2, RoundedCornerShape(16.dp))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { focusManager.clearFocus() }
            )
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow),
                contentDescription = stringResource(R.string.button_description_arrow),
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable(onClick = goBack)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = departure,
                        onValueChange = { editDeparture(it.filterCyrillic(context)) },
                        singleLine = true,
                        cursorBrush = SolidColor(White),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { focusRequester.requestFocus() }),
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
                            .weight(1f)
                            .onFocusChanged { departureIsFocused = it.isFocused }
                    )

                    Icon(
                        painter = painterResource(R.drawable.change),
                        contentDescription = stringResource(R.string.button_description_change),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                onClick = {
                                    editDeparture(arrival)
                                    editArrival(departure)
                                }
                            )
                            .padding(8.dp)
                    )
                }

                HorizontalDivider(color = Grey4)

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = arrival,
                        onValueChange = { editArrival(it.filterCyrillic(context)) },
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
                            .weight(1f)
                            .focusRequester(focusRequester)
                            .onFocusChanged { arrivalIsFocused = it.isFocused }
                    )

                    Icon(
                        painter = painterResource(R.drawable.close),
                        contentDescription = stringResource(R.string.button_description_close),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(onClick = { editArrival("") })
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}