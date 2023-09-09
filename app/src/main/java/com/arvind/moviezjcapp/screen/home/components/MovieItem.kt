package com.arvind.moviezjcapp.screen.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.common_components.CastItem
import com.arvind.moviezjcapp.ui.theme.AppOnPrimaryColor
import com.arvind.moviezjcapp.ui.theme.AppPrimaryColor
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun MovieItem(
    imageUrl: String,
    modifier: Modifier,
    onclick: () -> Unit
) {

    CoilImage(
        imageModel = imageUrl,
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
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onclick() },
        contentDescription = "Movie banner"
    )

}

@Composable
@Preview(showBackground = true)
fun MovieItemPreview() {
    MovieItem(
        modifier = Modifier,
        imageUrl = "https://picsum.photos/seed/picsum/200/300",
        onclick = {}
    )
}