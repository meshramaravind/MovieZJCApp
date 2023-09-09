package com.arvind.moviezjcapp.model

import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.screen.destinations.*

sealed class BottomNavItem(
    var title: String,
    var icon: Int,
    var destination: Destination
) {
    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_baseline_home_24,
        destination = HomeScreenDestination
    )

    object Search : BottomNavItem(
        title = "Search",
        icon = R.drawable.ic_baseline_search_24,
        destination = SearchScreenDestination
    )

    object Saved : BottomNavItem(
        title = "Saved",
        icon = R.drawable.ic_baseline_bookmark_24,
        destination = SavedScreenDestination
    )

    object Account : BottomNavItem(
        title = "Me",
        icon = R.drawable.ic_profile,
        destination = ProfileScreenDestination
    )
}