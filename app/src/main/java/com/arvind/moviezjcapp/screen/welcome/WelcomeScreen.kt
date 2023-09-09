package com.arvind.moviezjcapp.screen.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arvind.moviezjcapp.domain.models.OnBoarding
import com.arvind.moviezjcapp.screen.destinations.HomeScreenDestination
import com.arvind.moviezjcapp.screen.welcome.components.PagerScreen
import com.arvind.moviezjcapp.screen.welcome.viewmodel.WelcomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator,
    welcomeViewModel: WelcomeViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoarding.First,
        OnBoarding.Second,
        OnBoarding.Third
    )
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = 3,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(
                onBoarding = pages[position],
                onFinishClick = {
                    navigator.popBackStack()
                    navigator.navigate(HomeScreenDestination)
                    welcomeViewModel.saveOnBoardingState(completed = true)
                },
                pagerState = pagerState
            )

        }

    }
}


@Composable
@Preview(showBackground = true)
fun WelcomeScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
      
    }
}