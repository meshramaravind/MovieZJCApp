package com.arvind.moviezjcapp.screen.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.arvind.moviezjcapp.screen.film_details.FilmDetailsViewModel
import com.arvind.moviezjcapp.screen.tabs.component.SimilarDetails
import com.arvind.moviezjcapp.utils.FilmType
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SimilarScreen(
    selectedFilmType: FilmType,
    filmId: Int,
    filmDetailsViewModel: FilmDetailsViewModel = hiltViewModel()
) {
    val filmType: FilmType = remember { selectedFilmType }
    val similarFilms = filmDetailsViewModel.similarFilm.value.collectAsLazyPagingItems()

    LaunchedEffect(key1 = filmId) {
        filmDetailsViewModel.getSimilarFilm(filmId = filmId, filmType)
    }

    SimilarDetails(similarFilms)

}

@Composable
@Preview(showBackground = true)
fun SimilarScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        SimilarScreen(
            selectedFilmType = FilmType.MOVIE,
            filmId = 550,
            filmDetailsViewModel = hiltViewModel()
        )

    }
}