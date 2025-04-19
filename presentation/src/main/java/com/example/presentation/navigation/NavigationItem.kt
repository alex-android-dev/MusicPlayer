package com.example.presentation.navigation

import com.example.presentation.R
import com.example.presentation.navigation.ScreenRoute

sealed class NavigationItem(
    val screen: ScreenRoute,
    val iconResourceId: Int,
) {

    object Api : NavigationItem(
        screen = ScreenRoute.TrackListApi,
        iconResourceId = R.drawable.ic_music_note,
    )

    object Downloaded : NavigationItem(
        screen = ScreenRoute.TrackListDownloaded,
        iconResourceId = R.drawable.ic_downloaded,
    )

}