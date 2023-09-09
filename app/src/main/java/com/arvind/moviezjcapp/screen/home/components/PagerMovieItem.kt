package com.arvind.moviezjcapp.screen.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.common_components.PageIndicator
import com.arvind.moviezjcapp.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerMovieItem(
    imageUrl: String,
    modifier: Modifier,
    pagerState: PagerState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CoilImage(
            imageModel = imageUrl,
            modifier = modifier.fillMaxSize(),
            shimmerParams = ShimmerParams(
                baseColor = AppPrimaryColor,
                highlightColor = primaryPurpleColor,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            failure = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_not_available),
                        contentDescription = "no image"
                    )
                }
            },
            previewPlaceholder = R.drawable.ic_placeholder,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000),
            contentDescription = "Movie banner"
        )

        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            HorizontalPagerIndicator(
                modifier = Modifier,
                pagerState = pagerState,
                activeColor = TabIndicatorColor,
                inactiveColor = Color.Gray,
                indicatorWidth = INDICATOR_WIDTH,
                spacing = SMALL_PADDING,
            )

        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun PagerMovieItemPreview() {
    PagerMovieItem(
        imageUrl = "https://picsum.photos/seed/picsum/200/300",
        modifier = Modifier,
        pagerState = PagerState(currentPage = 1),
    )
}