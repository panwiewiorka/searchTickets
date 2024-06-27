package com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Grey2
import com.testproject1.searchtickets.presentation.theme.Grey5
import com.testproject1.searchtickets.presentation.theme.Grey6
import com.testproject1.searchtickets.presentation.theme.White
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchOptions(
    departureDate: Long?,
    arrivalDate: Long?,
    changeDepartureDate: (Long) -> Unit,
    changeArrivalDate: (Long?) -> Unit,
) {
    val localDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0)).toInstant(ZoneOffset.UTC).toEpochMilli()
    var showDeparturePicker by remember {mutableStateOf(false)}
    var showArrivalPicker by remember {mutableStateOf(false)}
    val departurePickerState = rememberDatePickerState(initialSelectedDateMillis = localDate)
    val arrivalPickerState = rememberDatePickerState()

    fun Long.toFormattedDate(): AnnotatedString {
        val formatter = SimpleDateFormat("dd MMM, EEE", Locale.getDefault())
        val formattedDate = formatter.format(this).lowercase().split(',')

        return buildAnnotatedString {
            append(formattedDate[0])
            withStyle(style = SpanStyle(Grey6)) { append("," + formattedDate[1]) }
        }
    }

    LaunchedEffect(Unit) {
        if (departureDate == null) changeDepartureDate(localDate)
    }

    if (showDeparturePicker) DatePickerDialog(
        onDismissRequest = { showDeparturePicker = false },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                TextButton(
                    onClick = {
                        showDeparturePicker = false
                    },
                ) {
                    Text(text = stringResource(R.string.button_text_cancel))
                }

                Button(onClick = {
                    if (arrivalDate != null && arrivalDate < departurePickerState.selectedDateMillis!!) {
                        arrivalPickerState.setSelection(null)
                        changeArrivalDate(null)
                    }
                    changeDepartureDate(departurePickerState.selectedDateMillis!!)
                    showDeparturePicker = false
                }) {
                    Text(text = stringResource(R.string.button_text_choose_date))
                }
            }
        }
    ) {
        DatePicker(
            state = departurePickerState,
            dateValidator = { it >= localDate },
        )
    }

    if (showArrivalPicker) DatePickerDialog(
        onDismissRequest = { showArrivalPicker = false },
        confirmButton = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                TextButton(
                    onClick = {
                        if (arrivalDate != null) changeArrivalDate(null)
                        arrivalPickerState.setSelection(null)
                        showArrivalPicker = false
                              },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = if (arrivalDate == null) stringResource(R.string.button_text_cancel) else stringResource(R.string.button_text_clear),
                    )
                }

                Button(
                    enabled = arrivalPickerState.selectedDateMillis != null,
                    onClick = {
                        changeArrivalDate(arrivalPickerState.selectedDateMillis!!)
                        showArrivalPicker = false
                    }
                ) {
                    Text(text = stringResource(R.string.button_text_choose_date))
                }
            }
        }
    ) {
        DatePicker(
            state = arrivalPickerState,
            dateValidator = { it >= departureDate!! },
        )
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        item { Spacer(modifier = Modifier.width(8.dp)) }
        item { SearchOption((departureDate ?: localDate).toFormattedDate(), null) { showDeparturePicker = true } }
        item { SearchOption(
            text = arrivalDate?.toFormattedDate() ?: AnnotatedString(stringResource(R.string.button_text_date_arrival)),
            icon = if (arrivalDate != null) null else R.drawable.plus) { showArrivalPicker = true } }
        item { SearchOption(AnnotatedString(stringResource(R.string.button_text_class)), R.drawable.person) {} }
        item { SearchOption(AnnotatedString(stringResource(R.string.button_text_filters)), R.drawable.filter) { } }
        item { Spacer(modifier = Modifier.width(8.dp)) }
    }
}

@Composable
fun SearchOption(text: AnnotatedString, icon: Int?, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(CircleShape)
            .background(Grey2)
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        if (icon != null) {
            Icon(
                painterResource(icon),
                contentDescription = null,
                tint = if (icon == R.drawable.person) Grey5 else White,
                modifier = Modifier.padding(end = 4.dp)
            )
        }

        Text(
            text = text,
            style = MaterialTheme.typography.displaySmall,
            color = White
        )
    }
}