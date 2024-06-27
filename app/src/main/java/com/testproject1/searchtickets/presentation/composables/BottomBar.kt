package com.testproject1.searchtickets.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.testproject1.searchtickets.NavList
import com.testproject1.searchtickets.R
import com.testproject1.searchtickets.presentation.theme.Blue
import com.testproject1.searchtickets.presentation.theme.Grey6

@Composable
fun BottomBar(
    navController: NavController,
    searchDestinationWindowIsVisible: Boolean,
    showSearchDestinationWindow: (Boolean) -> Unit,
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: NavList.TicketsScreen.name

    Column(
        modifier = Modifier.blur((if (searchDestinationWindowIsVisible && currentScreen == NavList.TicketsScreen.name) 10.dp else 0.dp), BlurredEdgeTreatment.Unbounded)
    ) {
        Divider()
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val modifier = Modifier.weight(1f)
            NavButton(modifier, R.drawable.airplane, NavList.TicketsScreen, currentScreen, navController) { showSearchDestinationWindow(false) }
            NavButton(modifier, R.drawable.hotel, NavList.HotelsScreen, currentScreen, navController)
            NavButton(modifier, R.drawable.location, NavList.ShortcutsScreen, currentScreen, navController)
            NavButton(modifier, R.drawable.bell, NavList.SubsScreen, currentScreen, navController)
            NavButton(modifier, R.drawable.person1, NavList.ProfileScreen, currentScreen, navController)
        }
    }
}

@Composable
fun NavButton(modifier: Modifier, icon: Int, screen: NavList, currentScreen: String, navController: NavController, onClick: () -> Unit = {}) {
    val onTicketsScreen = screen == NavList.TicketsScreen && (currentScreen == NavList.ArrivalChosenScreen.name || currentScreen == NavList.AllTicketsScreen.name)
    val color = if (currentScreen == screen.name || onTicketsScreen) Blue else Grey6
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    onClick()
                    navController.navigate(screen.name)
                }
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(icon), stringResource(screen.title), tint = color)
        Text(
            text = stringResource(screen.title),
            style = MaterialTheme.typography.labelSmall,
            color = color,
            maxLines = 1,
            softWrap = false,
            overflow = TextOverflow.Visible
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBottomBar() {
    val navController = NavController(LocalContext.current)
    BottomBar(navController, false, {})
}