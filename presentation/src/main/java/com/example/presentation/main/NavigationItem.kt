package com.example.presentation.presentation.main

import com.example.musicplayer.R
import com.example.presentation.navigation.Screen

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