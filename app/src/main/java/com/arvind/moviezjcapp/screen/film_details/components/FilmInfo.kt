package com.arvind.moviezjcapp.screen.film_details.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.screen.tabs.AboutScreen
import com.arvind.moviezjcapp.screen.tabs.EpisodeScreen
import com.arvind.moviezjcapp.screen.tabs.SimilarScreen
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.FilmTabType
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import com.arvind.moviezjcapp.utils.hourMinutes
import com.google.accompanist.pager.*
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FilmInfo(
    scrollState: LazyListState,
    releaseDate: String,
    overview: String,
    casts: Resource<CastDetailsApiResponse>,
    navigator: DestinationsNavigator,
    original_title: String,
    runTime: Int,
    details: Resource<MoviesDetails>,
    selectedFilmType: FilmType,
    filmId: Int,
) {
    val pagerState = rememberPagerState()
    Spacer(modifier = Modifier.height(10.dp))

    LazyColumn(contentPadding = PaddingValues(top = AppBarExpendedHeight), state = scrollState) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp),
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text =
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = PrimaryGray)) {
                            append(releaseDate)
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(" • ")
                        }
                        details.data?.genres?.forEach { genres ->
                            withStyle(style = SpanStyle(color = PrimaryGray)) {
                                append("${genres.name}, ")
                            }
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(" • ")
                        }
                        withStyle(style = SpanStyle(color = PrimaryGray)) {
                            append(runTime.hourMinutes())
                        }
                    },
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(size = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = GrapeFruitColor),
                        modifier = Modifier.weight(1f),
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Icons.Filled.PlayArrow,
                            tint = Color.White,
                            contentDescription = stringResource(id = R.string.play)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Play",
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(size = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = blackPearl),
                        modifier = Modifier.weight(1f),

                        ) {
                        Icon(
                            modifier = Modifier
                                .size(20.dp),
                            imageVector = Icons.Filled.Download,
                            tint = Color.White,
                            contentDescription = stringResource(id = R.string.download)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Download",
                            color = Color.White,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                ExpandableText(text = overview)
            }

        }

        item {
            if (casts is Resource.Success) {
                TabScreen(pagerState = pagerState)
                TabsContent(
                    pagerState = pagerState,
                    casts.data,
                    details.data,
                    selectedFilmType,
                    filmId,
                    navigator,
                )
            }
        }

//        TabDetails(casts, details, selectedFilmType, filmId, navigator, pagerState)

    }
}

@OptIn(ExperimentalPagerApi::class)
fun LazyListScope.TabDetails(
    casts: Resource<CastDetailsApiResponse>,
    details: Resource<MoviesDetails>,
    selectedFilmType: FilmType,
    filmId: Int,
    navigator: DestinationsNavigator,
    pagerState: PagerState
) {
    item {
        if (casts is Resource.Success) {
            TabScreen(pagerState = pagerState)
            TabsContent(
                pagerState = pagerState,
                casts.data,
                details.data,
                selectedFilmType,
                filmId,
                navigator,
            )
        }
    }
}

@Composable
fun ExpandableText(
    text: String,
    modifier: Modifier = Modifier,
    minimizedMaxLines: Int = 3,
) {
    var cutText by remember(text) { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    val seeMoreSizeState = remember { mutableStateOf<IntSize?>(null) }
    val seeMoreOffsetState = remember { mutableStateOf<Offset?>(null) }

    // getting raw values for smart cast
    val textLayoutResult = textLayoutResultState.value
    val seeMoreSize = seeMoreSizeState.value
    val seeMoreOffset = seeMoreOffsetState.value

    LaunchedEffect(text, expanded, textLayoutResult, seeMoreSize) {
        val lastLineIndex = minimizedMaxLines - 1
        if (!expanded && textLayoutResult != null && seeMoreSize != null
            && lastLineIndex + 1 == textLayoutResult.lineCount
            && textLayoutResult.isLineEllipsized(lastLineIndex)
        ) {
            var lastCharIndex =
                textLayoutResult.getLineEnd(lastLineIndex, visibleEnd = true) + 1
            var charRect: Rect
            do {
                lastCharIndex -= 1
                charRect = textLayoutResult.getCursorRect(lastCharIndex)
            } while (
                charRect.left > textLayoutResult.size.width - seeMoreSize.width
            )
            seeMoreOffsetState.value =
                Offset(charRect.left, charRect.bottom - seeMoreSize.height)
            cutText = text.substring(startIndex = 0, endIndex = lastCharIndex)
        }
    }

    Box(modifier) {
        Text(
            color = Color.LightGray,
            text = cutText ?: text,
            fontSize = 13.sp,
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    expanded = false
                },
            maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResultState.value = it },
        )
        if (!expanded) {
            val density = LocalDensity.current
            Text(
                color = primaryPurpleColor,
                text = "... Read more",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                onTextLayout = { seeMoreSizeState.value = it.size },
                modifier = Modifier
                    .then(
                        if (seeMoreOffset != null)
                            Modifier.offset(
                                x = with(density) { seeMoreOffset.x.toDp() },
                                y = with(density) { seeMoreOffset.y.toDp() },
                            )
                        else Modifier
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        expanded = true
                        cutText = null
                    }
                    .alpha(if (seeMoreOffset != null) 1f else 0f)
            )
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(pagerState: PagerState) {
    val list = listOf(
        FilmTabType.EPISODE, FilmTabType.SIMILAR, FilmTabType.ABOUT
    )
    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = TabIndicatorColor
            )
        }
    ) {
        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index].name,
                        color = if (pagerState.currentPage == index) TabTitleColor else PrimaryGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    castData: CastDetailsApiResponse?,
    movieData: MoviesDetails?,
    selectedFilmType: FilmType,
    filmId: Int,
    navigator: DestinationsNavigator
) {
    HorizontalPager(
        state = pagerState,
        count = 3,
    ) { page ->
        when (page) {
            0 -> EpisodeScreen()
            1 -> SimilarScreen(selectedFilmType, filmId)
            2 -> AboutScreen(castData, movieData, navigator, movieData?.genres)
        }
    }

}


@Composable
@Preview(showBackground = true)
fun FilmInfoPreview() {

}