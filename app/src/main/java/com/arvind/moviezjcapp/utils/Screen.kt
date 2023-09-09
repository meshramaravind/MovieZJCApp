package com.arvind.moviezjcapp.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.screen.destinations.*

sealed class Screen(
    var destination: Destination,
    @StringRes val title: Int? = null,
    @DrawableRes val icon: Int? = null,
) {

    object Profile : Screen(
        destination = MyProfileScreenDestination,
        title = R.string.profile,
        icon = R.drawable.ic_profile,
    )

    object Notification : Screen(
        destination = NotificationsScreenDestination,
        title = R.string.notification,
        icon = R.drawable.ic_baseline_notifications_none_24,
    )

    object History : Screen(
        destination = HistoryScreenDestination,
        title = R.string.history,
        icon = R.drawable.ic_baseline_history_24,
    )

    object MySubscription : Screen(
        destination = MySubscriptionScreenDestination,
        title = R.string.my_subscription,
        icon = R.drawable.ic_baseline_subscriptions_24,
    )

    object Settings : Screen(
        destination = SettingsScreenDestination,
        title = R.string.settings,
        icon = R.drawable.ic_baseline_settings_24,
    )

    object Help : Screen(
        destination = HelpScreenDestination,
        title = R.string.help,
        icon = R.drawable.ic_baseline_help_outline_24,
    )

    object Logout : Screen(
        destination = HelpScreenDestination,
        title = R.string.logout,
        icon = R.drawable.ic_baseline_logout_24,
    )


}
