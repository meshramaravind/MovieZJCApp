package com.arvind.moviezjcapp.screen.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arvind.moviezjcapp.screen.search.SearchViewModel
import com.arvind.moviezjcapp.ui.theme.PurplyBlue
import com.arvind.moviezjcapp.ui.theme.lightGray
import com.arvind.moviezjcapp.ui.theme.primaryDarkVariant
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBar(
    autoFocus: Boolean,
    viewModel: SearchViewModel = hiltViewModel(),
    onSearch: () -> Unit
) {

    Box(
        modifier = Modifier
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
            .clip(CircleShape)
            .background(PurplyBlue)
            .fillMaxWidth()
            .height(54.dp)
    ) {
        var searchInput: String by remember { mutableStateOf("") }
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current

        LaunchedEffect(key1 = searchInput) {
            if (viewModel.searchQuery.value.trim().isNotEmpty() &&
                viewModel.searchQuery.value.trim().length != viewModel.previousSearch.value.length
            ) {
                delay(750)
                onSearch()
                viewModel.previousSearch.value = searchInput.trim()
            }
        }

        TextField(
            value = searchInput,
            onValueChange = { newValue ->
                searchInput = if (newValue.trim().isNotEmpty()) newValue else ""
                viewModel.searchQuery.value = searchInput
            },
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester = focusRequester),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Search by title,genre,actor",
                    color = lightGray.copy(alpha = 0.8F)
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                textColor = Color.White.copy(alpha = 0.78F),
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.LightGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ), keyboardOptions = KeyboardOptions(
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    if (viewModel.searchQuery.value.trim().isNotEmpty()) {
                        focusManager.clearFocus()
                        viewModel.searchQuery.value = searchInput
                        if (searchInput != viewModel.previousSearch.value) {
                            viewModel.previousSearch.value = searchInput
                            onSearch()
                        }
                    }
                }
            ),
            leadingIcon = {
                LaunchedEffect(Unit) {
                    if (autoFocus) {
                        focusRequester.requestFocus()
                    }
                }
                Row {
                    IconButton(onClick = {
                        if (viewModel.searchQuery.value.trim().isNotEmpty()) {
                            focusManager.clearFocus()
                            viewModel.searchQuery.value = searchInput
                            if (searchInput != viewModel.previousSearch.value) {
                                viewModel.previousSearch.value = searchInput
                                onSearch()
                            }
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = com.arvind.moviezjcapp.R.drawable.ic_baseline_search_24),
                            tint = lightGray,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp, 30.dp)
                        )
                    }
                }
            },
            trailingIcon = {
                AnimatedVisibility(visible = searchInput.trim().isNotEmpty()) {
                    IconButton(onClick = {
                        focusManager.clearFocus()
                        searchInput = ""
                        viewModel.searchQuery.value = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            tint = lightGray,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    SearchBar(autoFocus = true,
        viewModel = hiltViewModel(),
        onSearch = {})
}