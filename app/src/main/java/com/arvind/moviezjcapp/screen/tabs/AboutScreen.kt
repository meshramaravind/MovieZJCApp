package com.arvind.moviezjcapp.screen.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.domain.models.Genres
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.screen.film_details.components.CastDetails
import com.arvind.moviezjcapp.screen.tabs.component.AboutItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AboutScreen(
    castData: CastDetailsApiResponse?,
    movieData: MoviesDetails?,
    navigator: DestinationsNavigator,
    genres: List<Genres>?,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp)
    ) {

        AboutItem(
            movieData = movieData,
            genres = genres
        )

        CastDetails(
            castData,
            navigator = navigator
        )
    }
}