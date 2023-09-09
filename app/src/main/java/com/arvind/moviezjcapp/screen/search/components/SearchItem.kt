package com.arvind.moviezjcapp.screen.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.domain.models.MultiSearch
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.Constants
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun SearchItem(
    searchItem: MultiSearch?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(SMALL_PADDING)
                .wrapContentHeight()
                .clickable { onClick() },
            backgroundColor = EbonyClayColor,
            shape = RoundedCornerShape(size = MEDIUM_PADDING)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    imageModel = "${Constants.IMAGE_BASE_URL}/${searchItem?.posterPath}",
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
                                painter = painterResource(id = R.drawable.image_not_available),
                                contentDescription = "no image"
                            )
                        }
                    },
                    previewPlaceholder = R.drawable.ic_placeholder,
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 1000),
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .size(150.dp),
                    contentDescription = "Search Movie item"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                ) {

                    Text(
                        text = (searchItem?.name ?: searchItem?.originalName
                        ?: searchItem?.originalTitle ?: "No Title Provided"),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(SMALL_PADDING))
                    Text(
                        text = searchItem?.overview.toString(),
                        fontSize = 14.sp,
                        color = Color.White.copy(0.6f),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

        }
    }

}

@Preview
@Composable
fun SearchItemPreview() {

}
