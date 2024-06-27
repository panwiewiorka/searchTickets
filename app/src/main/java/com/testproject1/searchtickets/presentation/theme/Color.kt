package com.testproject1.searchtickets.presentation.theme

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Black = Color(0xFF0c0c0c)
val Grey1 = Color(0xFF1d1e20)
val Grey2 = Color(0xFF2f3035)
//val Grey2 = Color(0xFF242529)
val Grey4 = Color(0xFF3e3f43)
val Grey5 = Color(0xFF5e5f61)
val Grey6 = Color(0xFF9f9f9f)
val Grey7 = Color(0xFFdbdbdb)
val White = Color(0xFFffffff)

val Blue = Color(0xFF2269bc)
//val Blue = Color(0xFF4C95FE)
val DarkBlue = Color(0xFF00427d)
val Green = Color(0xFF3a633b)
//val Green = Color(0xFF4CB24E)
val DarkGreen = Color(0xFF1e341f)
//val DarkGreen = Color(0xFF015905)
val Red = Color(0xFFff5d5d)
//val Red = Color(0xFFFF0000)
val Orange = Color(0xFFf36e36)

@Composable
fun myTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.colors(
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedTextColor = Color.Transparent,
        unfocusedTextColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    )
}