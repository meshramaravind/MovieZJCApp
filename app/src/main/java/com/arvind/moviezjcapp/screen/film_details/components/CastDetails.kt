package com.arvind.moviezjcapp.screen.film_details.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arvind.moviezjcapp.common_components.CastItem
import com.arvind.moviezjcapp.common_components.CircularBackButtons
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.domain.models.responses.TVCreditsApiResponse
import com.arvind.moviezjcapp.screen.destinations.AboutScreenDestination
import com.arvind.moviezjcapp.screen.destinations.CastsListScreenDestination
import com.arvind.moviezjcapp.screen.destinations.EpisodeScreenDestination
import com.arvind.moviezjcapp.screen.destinations.SimilarScreenDestination
import com.arvind.moviezjcapp.screen.tabs.AboutScreen
import com.arvind.moviezjcapp.screen.tabs.EpisodeScreen
import com.arvind.moviezjcapp.screen.tabs.SimilarScreen
import com.arvind.moviezjcapp.ui.theme.PrimaryGray
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.arvind.moviezjcapp.utils.Constants
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Composable
fun CastDetails(
    creditsResponse: CastDetailsApiResponse?,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
    ) {
        Text(
            text = "Actors",
            fontSize = 14.sp,
            color = PrimaryGray,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Cast",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White,
            )
            TextButton(
                onClick = {
                    Timber.d("${creditsResponse == null}")

                    if (creditsResponse == null) {
                        return@TextButton
                    }
                    navigator.navigate(CastsListScreenDestination(creditsResponse))
                },
                shape = RoundedCornerShape(size = 30.dp),
                modifier = Modifier.padding(
                    top = 10.dp,
                    bottom = 10.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "View all",
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(id = com.arvind.moviezjcapp.R.drawable.ic_baseline_chevron_right_24),
                        tint = primaryPurpleColor,
                        contentDescription = null
                    )
                }
            }
        }

        LazyRow(content = {
            items(creditsResponse?.casts!!) { cast ->
                CastItem(
                    size = 150.dp,
                    castImageUrl = "${Constants.IMAGE_BASE_URL}/${cast.profilePath}",
                    castName = cast.name
                )
            }

        })
    }
}


@Composable
@Preview(showBackground = true)
fun CastDetailsPreview() {
}

