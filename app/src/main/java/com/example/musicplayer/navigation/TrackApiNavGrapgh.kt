package com.example.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.trackApiNavGraph(
    trackApiListContent: @Composable () -> Unit,
    playTrackContent: @Composable () -> Unit,
) {

    navigation(
        startDestination = ScreenRoute.TrackApiList.route,
        route = ScreenRoute.TrackApi.route,
    ) {

        composable(ScreenRoute.TrackApiList.route) {
            trackApiListContent()
        }
        composable(ScreenRoute.PlayTrack.route) {
            playTrackContent()
        }
    }
}