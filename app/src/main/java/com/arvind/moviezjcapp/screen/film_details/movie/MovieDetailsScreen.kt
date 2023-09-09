package com.arvind.moviezjcapp.screen.film_details.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.screen.film_details.FilmDetailsViewModel
import com.arvind.moviezjcapp.screen.film_details.components.*
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.Constants
import com.arvind.moviezjcapp.utils.Constants.IMAGE_BASE_URL
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.utils.hourMinutes
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalPagerApi::class)
@Destination
@Composable
fun MovieDetailsScreen(
    filmId: Int,
    selectedFilmType: FilmType,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel(),
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<MoviesDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieDetails(filmId)
    }.value

    val casts = produceState<Resource<CastDetailsApiResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getMovieCasts(filmId)
    }.value

    Box(modifier = Modifier.fillMaxSize()) {
        if (details is Resource.Success) {
            FilmInfo(
                scrollState = scrollState,
                overview = details.data?.overview.toString(),
                releaseDate = details.data?.release_date.toString(),
                original_title = details.data?.original_title.toString(),
                runTime = details.data?.runtime!!,
                navigator = navigator,
                casts = casts,
                details = details,
                selectedFilmType = selectedFilmType,
                filmId = filmId,
            )

            FilmImageBanner(
                scrollState = scrollState,
                posterUrl = "${Constants.IMAGE_BASE_URL}/${details.data?.poster_path}",
                filmName = details.data?.title.toString(),
                filmId = details.data?.id!!,
                filmType = "movie",
                rating = details.data.vote_average?.toFloat()!!,
                navigator = navigator,
            )
        } else {
            CircularProgressIndicator()
        }

    }


}