package com.arvind.moviezjcapp.screen.tabs.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.compose.foundation.lazy.items
import com.arvind.moviezjcapp.domain.models.Film
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.arvind.moviezjcapp.utils.Constants.IMAGE_BASE_URL
import com.arvind.moviezjcapp.utils.FilmType
import com.arvind.moviezjcapp.utils.Resource
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun SimilarDetails(similarFilms: LazyPagingItems<Film>) {

    LazyRow(content = {
        items(similarFilms.itemSnapshotList) { thisMovie ->
            CoilImage(
                imageModel = "${IMAGE_BASE_URL}/${thisMovie!!.poster_path}",
                shimmerParams = ShimmerParams(
                    baseColor = primaryPurpleColor,
                    highlightColor = primaryPurpleColor,
                    durationMillis = 500,
                    dropOff = 0.65F,
                    tilt = 20F
                ),
                failure = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = com.arvind.moviezjcapp.R.drawable.image_not_available),
                            contentDescription = "no image"
                        )
                    }
                },
                previewPlaceholder = com.arvind.moviezjcapp.R.drawable.ic_placeholder,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 1000),
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .size(130.dp, 195.dp)
                    .clickable {

                    },
                contentDescription = "Similar Movie item"
            )
        }
    })

}
