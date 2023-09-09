package com.arvind.moviezjcapp.screen.welcome.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.domain.models.OnBoarding
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.Constants.CURRENT_PAGE
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun PagerScreen(
    onBoarding: OnBoarding,
    onFinishClick: () -> Unit,
    pagerState: PagerState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppPrimaryColor)
    )
    {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = onBoarding.image),
            contentDescription = stringResource(R.string.onboarding_image),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SHEET_PADDING),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = onBoarding.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h3,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = onBoarding.text,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.padding(SMALL_PADDING))

            HorizontalPagerIndicator(
                modifier = Modifier,
                pagerState = pagerState,
                activeColor = Purple700,
                inactiveColor = Color.White,
                indicatorWidth = INDICATOR_WIDTH,
                spacing = SMALL_PADDING
            )
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            FinishButton(onFinishClick = onFinishClick, pagerState = pagerState)
        }
    }
}

@ExperimentalPagerApi
@Composable
fun FinishButton(
    pagerState: PagerState,
    onFinishClick: () -> Unit
) {
    Box(
        modifier = Modifier.padding(LARGE_PADDING),
        contentAlignment = Alignment.BottomEnd
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == CURRENT_PAGE
        ) {
            Button(
                onClick = onFinishClick,
                modifier = Modifier.height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple700,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(30.dp)
            )
            {
                Text(text = stringResource(R.string.finish_button))
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun FirstPagerScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(
            onBoarding = OnBoarding.First,
            onFinishClick = {},
            pagerState = PagerState(currentPage = 1)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun SecondPagerScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(
            onBoarding = OnBoarding.Second,
            onFinishClick = {},
            pagerState = PagerState(currentPage = 1)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
@Preview(showBackground = true)
fun ThirdPagerScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(
            onBoarding = OnBoarding.Third,
            onFinishClick = {},
            pagerState = PagerState(currentPage = 1)
        )
    }
}

