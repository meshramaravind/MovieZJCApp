package com.arvind.moviezjcapp.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.compose.foundation.lazy.items
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.domain.models.*
import com.arvind.moviezjcapp.screen.destinations.MovieDetailsScreenDestination
import com.arvind.moviezjcapp.screen.destinations.SearchScreenDestination
import com.arvind.moviezjcapp.screen.destinations.SeeAllScreenDestination
import com.arvind.moviezjcapp.screen.home.components.MovieItem
import com.arvind.moviezjcapp.screen.home.components.PagerMovieItem
import com.arvind.moviezjcapp.screen.splash.components.LoopReverseLottieLoader
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.Constants.IMAGE_BASE_URL
import com.arvind.moviezjcapp.utils.FilmType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navigator = navigator,
            title = {
                Column {
                    Image(
                        painterResource(
                            id = R.drawable.moviez_jc_app_white_background
                        ),
                        contentDescription = "App logo",
                        modifier = Modifier
                            .size(width = 90.dp, height = 90.dp)
                            .padding(8.dp)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navActions = {
                IconButton(onClick = {
                    navigator.navigate(SearchScreenDestination)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_search_24),
                        contentDescription = null,
                        tint = PrimaryGray
                    )
                }
            }
        )

        NestedScroll(navigator = navigator, viewModel)
    }
}

@Composable
fun NestedScroll(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel
) {
    val trendingMovies = viewModel.trendingMovies.value.collectAsLazyPagingItems()
    val upcomingMovies = viewModel.upcomingMovies.value.collectAsLazyPagingItems()
    val topRatedMovies = viewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val nowPlayingMovies = viewModel.nowPlayingMovies.value.collectAsLazyPagingItems()
    val popularMovies = viewModel.popularMovies.value.collectAsLazyPagingItems()
    val selectedFilmType = viewModel.selectedFilmType.value

    val trendingTvSeries = viewModel.getTVTopRated.value.collectAsLazyPagingItems()
    val onAirTvSeries = viewModel.onAirTvSeries.value.collectAsLazyPagingItems()
    val tvAiringTodaySeries = viewModel.tvAiringToday.value.collectAsLazyPagingItems()
    val topRatedTvSeries = viewModel.topRatedMovies.value.collectAsLazyPagingItems()
    val popularTvSeries = viewModel.tvPopular.value.collectAsLazyPagingItems()

    LazyColumn {
        item {
            FilmCategory(
                filmTypes = listOf(FilmType.MOVIE, FilmType.TVSHOW),
                viewModel = viewModel
            )
        }
//        item {
//            Spacer(modifier = Modifier.height(20.dp))
//            PagerMovieItems(
//                pagingItems = upcomingMovies,
//                onErrorClick = {
//                    viewModel.refreshAll()
//                }
//            )
//        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Categories",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = SMALL_PADDING)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            val genres = viewModel.filmGenres
            LazyRow(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                items(count = genres.size) {
                    SelectableCategoriesChip(
                        genre = genres[it].name,
                        selected = genres[it].name == viewModel.selectedGenre.value.name,
                        onclick = {
                            if (viewModel.selectedGenre.value.name != genres[it].name) {
                                viewModel.selectedGenre.value = genres[it]
                                viewModel.filterBySetSelectedCategories(genre = genres[it])
                            }
                        }
                    )
                }
            }
        }

        item(content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "Trending Today",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(
                    onClick = {
                        navigator.navigate(
                            SeeAllScreenDestination(
                                selectedFilmType
                            )
                        )
                    },
                    shape = RoundedCornerShape(size = 30.dp),
                    modifier = Modifier.padding(
                        start = 20.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    )
                ) {
                    Text(
                        text = "see all",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }
        })

        item(content = {
            ScrollableMovieItems(
                navigator = navigator,
                pagingItems = trendingMovies,
                selectedFilmType = selectedFilmType,
                onErrorClick = {
                    viewModel.refreshAll()
                }
            )
        })

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Most Popular",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )

                TextButton(
                    onClick = { navigator.navigate(SeeAllScreenDestination(selectedFilmType)) },
                    shape = RoundedCornerShape(size = 30.dp),
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
                ) {
                    Text(
                        text = "see all",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }

        }
        item(content = {
            ScrollableMovieItems(
                navigator = navigator,
                pagingItems = popularMovies,
                selectedFilmType = selectedFilmType,
                onErrorClick = {
                    viewModel.refreshAll()
                }
            )
        })

        if (viewModel.selectedFilmType.value == FilmType.MOVIE) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (viewModel.selectedFilmType.value == FilmType.TVSHOW) {
                            "Tv Series"
                        } else {
                            "Upcoming"
                        },
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                    TextButton(
                        onClick = { navigator.navigate(SeeAllScreenDestination(selectedFilmType)) },
                        shape = RoundedCornerShape(size = 30.dp),
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 10.dp
                        )
                    ) {
                        Text(
                            text = "see all",
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                }
            }

            item {
                ScrollableMovieItems(
                    navigator = navigator,
                    pagingItems = upcomingMovies,
                    selectedFilmType = selectedFilmType,
                    onErrorClick = {
                        viewModel.refreshAll()
                    }
                )
            }
        }


        if (viewModel.selectedFilmType.value == FilmType.MOVIE) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = if (viewModel.selectedFilmType.value == FilmType.TVSHOW) {
                            "Airing Today"
                        } else {
                            "Now Playing"
                        },
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                    TextButton(
                        onClick = { navigator.navigate(SeeAllScreenDestination(selectedFilmType)) },
                        shape = RoundedCornerShape(size = 30.dp),
                        modifier = Modifier.padding(
                            top = 10.dp,
                            bottom = 10.dp
                        )
                    ) {
                        Text(
                            text = "see all",
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                }
            }

            item {
                ScrollableMovieItems(
                    navigator = navigator,
                    pagingItems = nowPlayingMovies,
                    selectedFilmType = selectedFilmType,
                    onErrorClick = {
                        viewModel.refreshAll()
                    }
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Top Rated",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                TextButton(
                    onClick = { navigator.navigate(SeeAllScreenDestination(selectedFilmType)) },
                    shape = RoundedCornerShape(size = 30.dp),
                    modifier = Modifier.padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
                ) {
                    Text(
                        text = "see all",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }
        }

        item {
            ScrollableMovieItems(
                navigator = navigator,
                pagingItems = topRatedMovies,
                selectedFilmType = selectedFilmType,
                onErrorClick = {
                    viewModel.refreshAll()
                }
            )
        }

    }
}


@Composable
fun SelectableCategoriesChip(
    genre: String,
    selected: Boolean,
    onclick: () -> Unit
) {
    val animateChipBackgroundColor by animateColorAsState(
        targetValue = if (selected) primaryPurpleColor else primaryPurplyBlue,
        animationSpec = tween(
            durationMillis = if (selected) 100 else 50,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        modifier = Modifier
            .padding(end = 10.dp)
            .clip(CircleShape)
            .background(
                color = animateChipBackgroundColor
            )
            .height(32.dp)
            .widthIn(min = 80.dp)
            .padding(horizontal = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onclick()
            }
    ) {
        Text(
            text = genre,
            fontWeight = if (selected) FontWeight.Normal else FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = if (selected) Color.White else Color.White.copy(alpha = 0.80F)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerMovieItems(
    pagingItems: LazyPagingItems<Film>,
    onErrorClick: () -> Unit
) {
    val pagerState = rememberPagerState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                LoopReverseLottieLoader(lottieFile = R.raw.loader)

            }
            is LoadState.NotLoading -> {
                pagingItems.itemSnapshotList.forEach { film ->
                    val imagePath = "$IMAGE_BASE_URL/${film!!.poster_path}"
                    HorizontalPager(
                        state = pagerState,
                        count =5,
                        contentPadding = PaddingValues(horizontal = 10.dp),
                    ) { page ->
                        PagerMovieItem(
                            imageUrl = imagePath,
                            modifier = Modifier
                                .height(250.dp),
                            pagerState = pagerState,
                        )
                    }

                    LaunchedEffect(key1 = pagerState.currentPage) {
                        launch {
                            delay(3000)
                            with(pagerState) {
                                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
                                tween<Float>(
                                    durationMillis = 500,
                                    easing = FastOutSlowInEasing
                                )
                                animateScrollToPage(page = target)
                            }
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

}

@Composable
private fun ScrollableMovieItems(
    navigator: DestinationsNavigator,
    pagingItems: LazyPagingItems<Film>,
    selectedFilmType: FilmType,
    onErrorClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
    ) {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                LoopReverseLottieLoader(lottieFile = R.raw.loader)
            }
            is LoadState.NotLoading -> {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(pagingItems.itemSnapshotList) { film ->
                        val imagePath = "$IMAGE_BASE_URL/${film!!.poster_path}"
                        MovieItem(
                            imageUrl = imagePath,
                            modifier = Modifier
                                .width(150.dp)
                                .height(220.dp)
                        ) {
                            navigator.navigate(
                                MovieDetailsScreenDestination(
                                    film.id,
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
}


@Composable
fun FilmCategory(
    filmTypes: List<FilmType>,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val selectedFilmType = viewModel.selectedFilmType.value
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        filmTypes.forEachIndexed { index, filmType ->
            val lineLength = animateFloatAsState(
                targetValue = if (filmType == viewModel.selectedFilmType.value) 2f else 0f,
                animationSpec = tween(
                    durationMillis = 300
                )
            )
            Text(
                text = if (filmType == FilmType.MOVIE) "Movies" else "Tv Shows",
                fontWeight = if (selectedFilmType == filmTypes[index]) FontWeight.Bold else FontWeight.Light,
                fontSize = if (selectedFilmType == filmTypes[index]) 24.sp else 16.sp,
                color = if (selectedFilmType == filmTypes[index])
                    Color.White else PrimaryGray,
                modifier = modifier
                    .padding(10.dp)
                    .drawBehind {
                        if (filmType == viewModel.selectedFilmType.value) {
                            if (lineLength.value > 0f) {
                                drawLine(
                                    color = if (filmType == viewModel.selectedFilmType.value) {
                                        primaryPurpleColor
                                    } else {
                                        lightGray
                                    },
                                    start = Offset(
                                        size.width / 2f - lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    end = Offset(
                                        size.width / 2f + lineLength.value * 10.dp.toPx(),
                                        size.height
                                    ),
                                    strokeWidth = 2.dp.toPx(),
                                    cap = StrokeCap.Round
                                )
                            }
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        if (viewModel.selectedFilmType.value != filmTypes[index]) {
                            viewModel.selectedFilmType.value = filmTypes[index]
                            viewModel.getFilmGenre()
                            viewModel.refreshAll(null)
                        }
                    }
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun FilmCategoryPreview() {
    FilmCategory(
        filmTypes = listOf(FilmType.MOVIE, FilmType.TVSHOW),
        modifier = Modifier,
        viewModel = hiltViewModel()
    )

}


