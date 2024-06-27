package com.testproject1.searchtickets.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testproject1.searchtickets.NavList
import com.testproject1.searchtickets.presentation.composables.BottomBar
import com.testproject1.searchtickets.presentation.composables.EmptyScreen
import com.testproject1.searchtickets.presentation.screens.tickets.t1_main.TicketsMainScreen
import com.testproject1.searchtickets.presentation.screens.tickets.t3_arrival_chosen.ArrivalChosenScreen
import com.testproject1.searchtickets.presentation.screens.tickets.t4_all_tickets.AllTicketsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavScreen(
    navController: NavHostController = rememberNavController(),
) {
    val mainViewModel: MainViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                searchDestinationWindowIsVisible = mainViewModel.searchDestinationWindowIsVisible,
                showSearchDestinationWindow = mainViewModel::showSearchDestinationWindow,
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = NavList.TicketsScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavList.TicketsScreen.name) {
                TicketsMainScreen(
                    offers = mainViewModel.offers,
                    departure = mainViewModel.departure,
                    arrival = mainViewModel.arrival,
                    editDeparture = mainViewModel::editDeparture,
                    editArrival = mainViewModel::editArrival,
                    searchDestinationWindowIsVisible = mainViewModel.searchDestinationWindowIsVisible,
                    showSearchDestinationWindow = mainViewModel::showSearchDestinationWindow,
                    hintPage = mainViewModel.hintPage,
                    changeHintPage = mainViewModel::changeHintPage,
                    goToArrivalChosenScreen = { navController.navigate(NavList.ArrivalChosenScreen.name) },
                )
            }

            composable(NavList.ArrivalChosenScreen.name) {
                ArrivalChosenScreen(
                    departure = mainViewModel.departure,
                    arrival = mainViewModel.arrival,
                    editDeparture = mainViewModel::editDeparture,
                    editArrival = mainViewModel::editArrival,
                    departureDate = mainViewModel.departureDate,
                    arrivalDate = mainViewModel.arrivalDate,
                    changeDepartureDate = mainViewModel::changeDepartureDate,
                    changeArrivalDate = mainViewModel::changeArrivalDate,
                    ticketOffers = mainViewModel.ticketOffers,
                    getTicketOffers = mainViewModel::getTicketOffers,
                    goBack = { navController.navigateUp() },
                    goToShowAllTicketsScreen = { navController.navigate(NavList.AllTicketsScreen.name) }
                )
            }

            composable(NavList.AllTicketsScreen.name) {
                AllTicketsScreen(
                    departure = mainViewModel.departure,
                    arrival = mainViewModel.arrival,
                    departureDate = mainViewModel.departureDate!!,
                    tickets = mainViewModel.tickets,
                    getTickets = mainViewModel::getTickets,
                    goBack = { navController.navigateUp() }
                )
            }

            composable(NavList.HotelsScreen.name) { EmptyScreen(NavList.HotelsScreen.title) }
            composable(NavList.ShortcutsScreen.name) { EmptyScreen(NavList.ShortcutsScreen.title) }
            composable(NavList.SubsScreen.name) { EmptyScreen(NavList.SubsScreen.title) }
            composable(NavList.ProfileScreen.name) { EmptyScreen(NavList.ProfileScreen.title) }
        }
    }
}