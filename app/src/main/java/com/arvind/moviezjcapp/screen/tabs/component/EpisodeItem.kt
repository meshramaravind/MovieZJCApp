package com.arvind.moviezjcapp.screen.tabs.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.screen.tabs.SimilarScreen
import com.arvind.moviezjcapp.ui.theme.blackPearl
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.arvind.moviezjcapp.utils.Constants
import com.arvind.moviezjcapp.utils.FilmType
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun EpisodeItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .wrapContentHeight(),
            backgroundColor = blackPearl,
            shape = RoundedCornerShape(size = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CoilImage(
                    imageModel = "https://picsum.photos/200/300",
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
                        .clip(RoundedCornerShape(20.dp))
                        .size(150.dp),
                    contentDescription = "Episode Movie item"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.6f)
                        .padding(start = 10.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Trailer",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "Download Icon",
                            modifier = Modifier.size(20.dp),
                            tint = Color.White,
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ",
                        fontSize = 14.sp,
                        color = Color.White.copy(0.6f),
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,

                        )
                }
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
fun EpisodeItemPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        EpisodeItem(
        )

    }
}
