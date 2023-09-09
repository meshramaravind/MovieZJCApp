package com.arvind.moviezjcapp.screen.tabs.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.moviezjcapp.domain.models.Genres
import com.arvind.moviezjcapp.domain.models.MoviesDetails
import com.arvind.moviezjcapp.screen.tabs.SimilarScreen
import com.arvind.moviezjcapp.ui.theme.PrimaryGray
import com.arvind.moviezjcapp.utils.FilmType

@Composable
fun AboutItem(
    movieData: MoviesDetails?,
    genres: List<Genres>?
) {
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Genre",
                fontSize = 14.sp,
                color = PrimaryGray,
            )

            Text(
                text = "Language",
                fontSize = 14.sp,
                color = PrimaryGray,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            genres?.forEach { genres ->
                Text(
                    text = "${genres.name},",
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.6f),
                )
            }

            Text(
                text = movieData?.original_language.toString(),
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.6f),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,

                )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Year",
                fontSize = 14.sp,
                color = PrimaryGray,
            )

            Text(
                text = "Status",
                fontSize = 14.sp,
                color = PrimaryGray,
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = movieData?.release_date.toString(),
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.6f),
            )

            Text(
                text = movieData?.status.toString(),
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.6f),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis

            )
        }
    }


}


