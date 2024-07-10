package com.testproject1.searchtickets

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

enum class NavList(@StringRes val title: Int) {
    TicketsScreen(title = R.string.screen_tickets),
    HotelsScreen(title = R.string.screen_hotels),
    ShortcutsScreen(title = R.string.screen_shortcuts),
    SubsScreen(title = R.string.screen_subs),
    ProfileScreen(title = R.string.screen_profile),

    ArrivalChosenScreen(title = R.string.screen_arrival_chosen),
    AllTicketsScreen(title = R.string.screen_all_tickets),

//    RouteHintScreen(title = R.string.hint_route),
//    DestinationHintScreen(title = R.string.hint_destination),
//    WeekendHintScreen(title = R.string.hint_weekend),
//    HotTicketsHintScreen(title = R.string.hint_hot_tickets),
}

const val TAG = "mytag"

sealed class UiText {
    data class DynamicString(
        val value: String
    ): UiText()

    class StringResourse(
        @StringRes val id: Int,
        vararg val args: Any
    ): UiText()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResourse -> context.getString(id, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResourse -> stringResource(id, *args)
        }
    }
}


fun String.filterCyrillic(context: Context): String {
    val re = Regex(UiText.StringResourse(R.string.alphabet).asString(context))
    return re.replace(this, "")
}

fun Int.toFormattedPrice(): String {
    return this.toString().reversed().chunked(3).joinToString(" ").reversed()
}