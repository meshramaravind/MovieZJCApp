package com.arvind.moviezjcapp.common_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvind.moviezjcapp.R

@Composable
fun CircularFavoriteButton(onClick: () -> Unit = {}) {
    Button(
        onClick = { onClick() },
        contentPadding = PaddingValues(),
        shape = CircleShape,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray
        )
    ) {
        androidx.compose.material.IconButton(onClick = { onClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_bookmark_24),
                tint = Color.White,
                contentDescription = null
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun CircularFavoriteButton() {
    CircularFavoriteButton(
        onClick = {})
}