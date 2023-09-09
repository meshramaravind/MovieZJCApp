package com.arvind.moviezjcapp.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.ui.theme.AppPrimaryColor
import com.arvind.moviezjcapp.ui.theme.lightGray
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CastItem(
    size: Dp,
    castName: String,
    castImageUrl: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Image(
//            painter = rememberAsyncImagePainter(
//                ImageRequest.Builder(LocalContext.current).data(data = castImageUrl)
//                    .apply(block = fun ImageRequest.Builder.() {
//                        placeholder(R.drawable.ic_placeholder)
//                        crossfade(true)
//                    }).build()
//            ),
//            modifier = Modifier
//                .fillMaxSize()
//                .size(size)
//                .padding(10.dp)
//                .clip(RoundedCornerShape(16.dp)),
//            contentScale = ContentScale.Crop,
//            contentDescription = "Character"
//        )


        CoilImage(
            imageModel = castImageUrl,
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
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
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
            modifier = Modifier
                .size(size)
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentDescription = "Cast Image",
        )
        Text(
            text = castName,
            color = lightGray,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 11.sp
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CastItemPreview() {
    CastItem(
        size = 100.dp,
        castName = "Carrie Coon",
        castImageUrl = "https://picsum.photos/seed/picsum/200/300"
    )
}