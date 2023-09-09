package com.arvind.moviezjcapp.screen.film_details.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor

@Composable
fun CircularFavoriteButtons(
    isLiked: Boolean,
    onClick: (isFav: Boolean) -> Unit = {}
) {
    IconButton(
        onClick = {
            onClick(isLiked)
        }) {

        Icon(
            modifier = Modifier
                .width(38.dp)
                .height(38.dp),
            imageVector = Icons.Filled.Bookmark,
            tint = if (isLiked) {
                primaryPurpleColor
            } else {
                Color.LightGray
            },
            contentDescription = if (isLiked) {
                stringResource(id = R.string.unlike)
            } else {
                stringResource(id = R.string.like)
            }
        )
    }
}