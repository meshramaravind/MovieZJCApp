package com.arvind.moviezjcapp.screen.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.screen.destinations.AboutScreenDestination
import com.arvind.moviezjcapp.screen.destinations.EpisodeScreenDestination
import com.arvind.moviezjcapp.screen.destinations.SimilarScreenDestination
import com.arvind.moviezjcapp.screen.film_details.components.CastDetails
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor
import com.arvind.moviezjcapp.utils.FilmTabType
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun EpisodeScreen() {
    Text(
        text = "About",
        color = Color.White
    )

}