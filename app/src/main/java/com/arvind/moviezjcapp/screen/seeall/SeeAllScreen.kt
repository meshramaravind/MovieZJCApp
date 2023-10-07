package com.arvind.moviezjcapp.screen.seeall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.compose.foundation.lazy.items
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.screen.destinations.MovieDetailsScreenDestination
import com.arvind.moviezjcapp.screen.home.HomeViewModel
import com.arvind.moviezjcapp.screen.home.components.MovieItem
import com.arvind.moviezjcapp.screen.splash.components.LoopReverseLottieLoader
import com.arvind.moviezjcapp.ui.theme.GrapeFruitColor
import com.arvind.moviezjcapp.utils.Constants
import com.arvind.moviezjcapp.utils.FilmType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import retrofit2.HttpException
import java.io.IOException

@Destination
@Composable
fun SeeAllScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel(),
    selectedFilmType: FilmType,
) {
    Column(
        Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "See All",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true
        )

        val trendingMovies = viewModel.trendingMovies.value.collectAsLazyPagingItems()

        GridMovieItems(
            navigator = navigator,
            pagingItems = trendingMovies,
            selectedFilmType = selectedFilmType,
            onErrorClick = {
                viewModel.refreshAll()
            }
        )


    }
}

@Composable
fun GridMovieItems(
    navigator: DestinationsNavigator,
    pagingItems: LazyPagingItems<Film>,
    selectedFilmType: FilmType,
    onErrorClick: () -> Unit
) {

    when (pagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            LoopReverseLottieLoader(lottieFile = R.raw.loader)
        }

        is LoadState.NotLoading -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(10.dp)
            ) {
                items(pagingItems.itemCount) { index ->
                    val imagePath =
                        "${Constants.IMAGE_BASE_URL}/${pagingItems[index]!!.poster_path}"
                    MovieItem(
                        imageUrl = imagePath,
                        modifier = Modifier
                            .width(150.dp)
                            .height(220.dp)
                    ) {
                        navigator.navigate(
                            MovieDetailsScreenDestination(
                                pagingItems[index]!!.id,
                                selectedFilmType = selectedFilmType
                            )
                        )
                    }
                }

            }


        }
        is LoadState.Error -> {
            val error = pagingItems.loadState.refresh as LoadState.Error
            val errorMessage = when (error.error) {
                is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                is IOException -> "Connection failed. Tap to retry!"
                else -> "Failed! Tap to retry!"
            }
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(161.25.dp) // maintain the vertical space between two categories
                    .clickable {
                        onErrorClick()
                    }
            ) {
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = GrapeFruitColor,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        else -> {
        }
    }


}


