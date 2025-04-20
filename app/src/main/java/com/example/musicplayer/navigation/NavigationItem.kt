package com.example.musicplayer.navigation

import com.example.presentation.R

sealed class NavigationItem(
    val screen: ScreenRoute,
    val iconResourceId: Int,
) {

    object Api : NavigationItem(
        screen = ScreenRoute.TrackApi,
        iconResourceId = R.drawable.ic_music_note,
    )

    object Downloaded : NavigationItem(
        screen = ScreenRoute.TrackLocalList,
        iconResourceId = R.drawable.ic_downloaded,
    )

}