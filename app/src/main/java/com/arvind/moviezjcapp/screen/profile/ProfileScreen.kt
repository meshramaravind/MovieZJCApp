package com.arvind.moviezjcapp.screen.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.arvind.moviezjcapp.R
import com.arvind.moviezjcapp.common_components.CustomAlertDialog
import com.arvind.moviezjcapp.common_components.DrawableButton
import com.arvind.moviezjcapp.common_components.IconButton
import com.arvind.moviezjcapp.common_components.StandardToolbar
import com.arvind.moviezjcapp.screen.destinations.*
import com.arvind.moviezjcapp.ui.theme.*
import com.arvind.moviezjcapp.utils.Screen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator
) {
    val generalOptions = remember {
        listOf(
            Screen.Profile, Screen.Notification, Screen.History,
            Screen.MySubscription, Screen.Settings, Screen.Help,
            Screen.Logout,
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            StandardToolbar(
                navigator = navigator,
                title = {
                    Text(
                        text = "Profile",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,

                    )
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = false
            )
            ProfileSection()
            Spacer(modifier = Modifier.height(30.dp))
        }
        items(generalOptions) { option ->
            ProfileOptionItem(
                icon = option.icon,
                title = option.title,
                onOptionClicked = {
                    if (option is Screen.Profile) {
                        navigator.navigate(MyProfileScreenDestination)
                    } else if (option is Screen.Notification) {
                        navigator.navigate(NotificationsScreenDestination)
                    } else if (option is Screen.History) {
                        navigator.navigate(HistoryScreenDestination)
                    } else if (option is Screen.MySubscription) {
                        navigator.navigate(MySubscriptionScreenDestination)
                    } else if (option is Screen.Settings) {
                        navigator.navigate(SettingsScreenDestination)
                    } else if (option is Screen.Help) {
                        navigator.navigate(HelpScreenDestination)
                    } else if (option is Screen.Logout) {
                        val logout: @Composable () -> Unit = {
                            CustomAlertDialog(
                                subTitle = R.string.deleteContent,
                                subTitleColor = primaryPurpleColor,
                                backgroundColor = Color.White,
                                positiveButton = R.string.yesDelete,
                                negativeButton = R.string.no,
                                positiveButtonColor = PrimaryGray,
                                negativeButtonColor = GrapeFruitColor
                            )
                        }

                    }
                },
            )


        }
    }
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.sample_avtar),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Antonio Renders",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "@renders_antonio",
            color = Color.White,
            fontSize = 14.sp,
        )
    }
}

@Composable
fun ProfileOptionItem(
    icon: Int?,
    title: Int?,
    onOptionClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .fillMaxWidth()
            .clickable { onOptionClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        DrawableButton(
            painter = rememberAsyncImagePainter(model = icon),
            backgroundColor = PrimaryGray.copy(alpha = 0.4f),
            iconTint = Color.White,
            onButtonClicked = {},
            iconSize = 20.dp,
            paddingValue = PaddingValues(10.dp),
            shape = CircleShape,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        title?.let {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
            )
        }
        IconButton(
            icon = Icons.Rounded.KeyboardArrowRight,
            backgroundColor = MaterialTheme.colors.background,
            iconTint = MaterialTheme.colors.onBackground,
            onButtonClicked = {},
            iconSize = smIcon,
            paddingValue = PaddingValues(md),
            shape = CircleShape,
        )
    }
}





