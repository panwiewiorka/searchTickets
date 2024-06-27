package com.testproject1.searchtickets

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.testproject1.searchtickets.presentation.NavScreen
import com.testproject1.searchtickets.presentation.theme.SearchTicketsTestProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchTicketsTestProjectTheme {
                NavScreen()
            }
        }
    }
}