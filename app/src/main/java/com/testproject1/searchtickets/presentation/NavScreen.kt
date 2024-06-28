package com.testproject1.searchtickets.presentation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun NavScreen(
    navController: NavHostController = rememberNavController(),
) {
    val mainViewModel: MainViewModel = hiltViewModel()
    val appState by mainViewModel.appState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                searchDestinationWindowIsVisible = appState.searchDestinationWindowIsVisible,
                showSearchDestinationWindow = mainViewModel::showSearchDestinationWindow,
            )
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = NavList.TicketsScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = NavList.TicketsScreen.name,
                enterTransition = {
                    if (initialState.destination.route == NavList.ArrivalChosenScreen.name) {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween())
                    } else fadeIn()
                                  },
                exitTransition = {
                    if (targetState.destination.route == NavList.ArrivalChosenScreen.name) {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween())
                    } else fadeOut()
                                 },
                popEnterTransition = {
                    if (initialState.destination.route == NavList.ArrivalChosenScreen.name) {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween())
                    } else fadeIn()
                                     },
                popExitTransition = {
                    if (targetState.destination.route == NavList.ArrivalChosenScreen.name) {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween())
                    } else fadeOut()
                                    },
            ) {
                TicketsMainScreen(
                    state = appState,
                    editDeparture = mainViewModel::editDeparture,
                    editArrival = mainViewModel::editArrival,
                    showSearchDestinationWindow = mainViewModel::showSearchDestinationWindow,
                    getOffers = mainViewModel::getOffers,
                    changeHintPage = mainViewModel::changeHintPage,
                    saveDepartureToDb = mainViewModel::saveToDb,
                    goToArrivalChosenScreen = { navController.navigate(NavList.ArrivalChosenScreen.name) },
                )
            }

            composable(
                route = NavList.ArrivalChosenScreen.name,
                enterTransition = {
                    if (initialState.destination.route == NavList.AllTicketsScreen.name) {
                        slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween())
                    } else fadeIn()
                },
                exitTransition = {
                    if (targetState.destination.route == NavList.AllTicketsScreen.name) {
                        slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween())
                    } else fadeOut()
                },
                popEnterTransition = {
                    if (initialState.destination.route == NavList.AllTicketsScreen.name) {
                        slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween())
                    } else fadeIn()
                                     },
                popExitTransition = {
                    if (targetState.destination.route == NavList.AllTicketsScreen.name) {
                        slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween())
                    } else fadeOut()
                                    },
            ) {
                ArrivalChosenScreen(
                    state = appState,
                    editDeparture = mainViewModel::editDeparture,
                    editArrival = mainViewModel::editArrival,
                    changeDepartureDate = mainViewModel::changeDepartureDate,
                    changeArrivalDate = mainViewModel::changeArrivalDate,
                    getTicketOffers = mainViewModel::getTicketOffers,
                    saveDepartureToDb = mainViewModel::saveToDb,
                    goBack = { navController.navigateUp() },
                    goToShowAllTicketsScreen = { navController.navigate(NavList.AllTicketsScreen.name) }
                )
            }

            composable(
                route = NavList.AllTicketsScreen.name,
                enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween()) },
                exitTransition = { fadeOut() },
                popEnterTransition = { fadeIn() },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween()) },
            ) {
                AllTicketsScreen(
                    state = appState,
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