package com.arvind.moviezjcapp.common_components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.ui.theme.PrimaryGray
import com.arvind.moviezjcapp.ui.theme.GrapeFruitColor
import com.arvind.moviezjcapp.ui.theme.primaryPurpleColor

@Composable
fun CustomAlertDialog(
    subTitle: Int,
    subTitleColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
    backgroundColor: Color = MaterialTheme.colors.surface,
    positiveButton: Int,
    positiveButtonColor: Color = MaterialTheme.colors.surface,
    negativeButton: Int,
    negativeButtonColor: Color = MaterialTheme.colors.surface,

    ) {
    val context = LocalContext.current
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            text = {
                Text(
                    stringResource(id = subTitle),
                    color = subTitleColor
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        stringResource(id = positiveButton),
                        color = positiveButtonColor
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        stringResource(id = negativeButton),
                        color = negativeButtonColor
                    )
                }
            },
            backgroundColor = backgroundColor,
            contentColor = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CustomAlertDialogPreview() {
    CustomAlertDialog(
        subTitle = R.string.deleteContent,
        subTitleColor = primaryPurpleColor,
        backgroundColor = Color.White,
        positiveButton = R.string.yesDelete,
        negativeButton = R.string.no,
        positiveButtonColor = GrapeFruitColor,
        negativeButtonColor = PrimaryGray,
    )
}