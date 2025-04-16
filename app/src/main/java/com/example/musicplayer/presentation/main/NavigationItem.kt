package com.example.musicplayer.presentation.main

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.musicplayer.R
import com.example.musicplayer.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val iconResourceId: Int,
) {

    object Api : NavigationItem(
        screen = Screen.TrackListApi,
        iconResourceId = R.drawable.ic_music_note,
    )

    object Downloaded : NavigationItem(
        screen = Screen.TrackListDownloaded,
        iconResourceId = R.drawable.ic_downloaded,
    )

}