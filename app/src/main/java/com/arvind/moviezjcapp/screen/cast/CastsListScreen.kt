package com.arvind.moviezjcapp.screen.cast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.common_components.CastItem
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.domain.models.responses.CastDetailsApiResponse
import com.arvind.moviezjcapp.utils.Constants
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@Destination
@Composable
fun CastsListScreen(
    castDetailsApiResponse: CastDetailsApiResponse,
    navigator: DestinationsNavigator
) {

    Column(modifier = Modifier.fillMaxSize()) {

        StandardToolbar(
            navigator = navigator,
            title = {
                Text(
                    text = "Casts",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            },
            showBackArrow = true
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(castDetailsApiResponse.casts) { cast ->

                Timber.d(cast.toString())

                val imageLink = if (cast.profilePath == "" || cast.profilePath == null) {
                    "https://pixy.org/src/9/94083.png"
                } else {
                    cast.profilePath
                }

                CastItem(
                    size = 170.dp,
                    castImageUrl = "${Constants.IMAGE_BASE_URL}/$imageLink",
                    castName = cast.name
                )

            }

        }

    }
}