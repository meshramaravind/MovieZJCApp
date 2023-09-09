package com.arvind.moviezjcapp.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.screen.search.components.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.domain.models.Genres
import com.arvind.moviezjcapp.domain.models.MultiSearch
import com.arvind.moviezjcapp.screen.destinations.MovieDetailsScreenDestination
import com.arvind.moviezjcapp.screen.destinations.TvSeriesDetailsScreenDestination
import com.arvind.moviezjcapp.screen.home.HomeViewModel
import com.arvind.moviezjcapp.screen.search.components.SearchItem
import com.arvind.moviezjcapp.ui.theme.primaryDarkVariant
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.arvind.moviezjcapp.utils.Constants
import com.arvind.moviezjcapp.utils.Constants.IMAGE_BASE_URL
import com.arvind.moviezjcapp.utils.FilmType
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalComposeUiApi::class)
@Destination(start = false)
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    searchViewModel: SearchViewModel = hiltViewModel(),
    viewModel: HomeViewModel = hiltViewModel()
) {

    val searchResult = searchViewModel.searchResultState.value.collectAsLazyPagingItems()
    val includeAdult = searchViewModel.includeAdult.value.collectAsState(initial = true)

    val selectedFilmType = viewModel.selectedFilmType.value

    Column(
        Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Search",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true
        )

        SearchBar(
            autoFocus = true,
            onSearch = {
                searchViewModel.searchAll(includeAdult.value ?: true)
            })

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 10.dp),
        ) {
            when (searchResult.loadState.refresh) {
                is LoadState.NotLoading -> {
                    items(searchResult) { search ->
                        SearchItem(
                            search,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            onClick = {
                                when (search?.mediaType) {
                                    "movie" -> {
                                        navigator.navigate(
                                            MovieDetailsScreenDestination(
                                                search.id!!,
                                                selectedFilmType
                                            )
                                        )
                                    }
                                    "tv" -> {
                                        navigator.navigate(TvSeriesDetailsScreenDestination(search.id!!))
                                    }
                                    else -> {
                                        return@SearchItem
                                    }
                                }
                            }
                        )
                    }
                    if (searchResult.itemCount == 0) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.no_match_found),
                                    contentDescription = null
                                )
                            }

                        }
                    }
                }

                is LoadState.Loading -> item {
                    if (searchViewModel.searchQuery.value.isNotEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                else -> item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.no_match_found),
                            contentDescription = null
                        )
                    }
                }

            }
        }
    }
}
