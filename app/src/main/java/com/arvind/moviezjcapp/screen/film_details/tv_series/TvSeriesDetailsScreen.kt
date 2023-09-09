package com.arvind.moviezjcapp.screen.film_details.tv_series

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.moviezjcapp.domain.models.TvSeriesDetails
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.screen.film_details.FilmDetailsViewModel
import com.arvind.moviezjcapp.utils.Resource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TvSeriesDetailsScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<TvSeriesDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvSeriesDetails(filmId)
    }.value

    val casts = produceState<Resource<CastDetailsApiResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvSeriesCasts(filmId)
    }.value

//    Box {
//        if (details is Resource.Success) {
//            FilmInfo(
//                scrollState = scrollState,
//                releaseDate = details.data?.firstAirDate.toString(),
//                overview = details.data?.overview.toString(),
//                casts = casts,
//                navigator = navigator,
//                original_title = details.data?.originalName.toString(),
//                runTime = details.data?.numberOfSeasons.toString(),
//                details = details.data
//            )
//            FilmImageBanner(
//                scrollState = scrollState,
//                posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.posterPath}",
//                filmName = details.data?.name.toString(),
//                filmId = details.data?.id!!,
//                filmType = "tv",
//                releaseDate = details.data.firstAirDate,
//                rating = details.data.voteAverage.toFloat(),
//                navigator = navigator,
//            )
//        } else {
//            CircularProgressIndicator()
//        }
//
//    }
}