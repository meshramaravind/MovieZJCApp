package com.arvind.moviezjcapp.domain.models

import androidx.annotation.DrawableRes
import com.arvind.moviezjcapp.R

sealed class OnBoarding(
    @DrawableRes
    val image: Int,
    val title: String,
    val text: String
) {
    object First : OnBoarding(
        image = R.drawable.johnsnow,
        title = "WELCOME",
        text = "Your Favourite Movies and Series brought right to you."
    )

    object Second : OnBoarding(
        image = R.drawable.ragnar,
        title = "FIND A MOVIE",
        text = "Watch and find movies that bring your mood back"
    )

    object Third : OnBoarding(
        image = R.drawable.viking,
        title = "REAL TIME",
        text = "Movie & TV information and updates movie trailers "
    )
}
