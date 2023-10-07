package com.arvind.moviezjcapp.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.screen.search.components.SearchBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.paging.LoadState
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.screen.destinations.MovieDetailsScreenDestination
import com.arvind.moviezjcapp.screen.destinations.TvSeriesDetailsScreenDestination
import com.arvind.moviezjcapp.screen.home.HomeViewModel
import com.arvind.moviezjcapp.screen.search.components.SearchItem


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
                    items(searchResult.itemSnapshotList) { search ->

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
