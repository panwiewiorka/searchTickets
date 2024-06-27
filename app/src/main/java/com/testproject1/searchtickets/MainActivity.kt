package com.testproject1.searchtickets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.testproject1.searchtickets.presentation.NavScreen
import com.testproject1.searchtickets.presentation.theme.SearchTicketsTestProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchTicketsTestProjectTheme {
                NavScreen()
            }
        }
    }
}