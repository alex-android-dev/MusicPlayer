package com.example.presentation.main

import com.example.presentation.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val iconResourceId: Int,
) {

    object Api : NavigationItem(
        screen = Screen.TrackListApi,
        iconResourceId = 0, // TODO
    )

    object Downloaded : NavigationItem(
        screen = Screen.TrackListDownloaded,
        iconResourceId = 0,
    )

}