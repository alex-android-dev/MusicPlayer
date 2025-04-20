package com.example.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.trackApiNavGraph(
    trackApiListContent: @Composable () -> Unit,
    playTrackContent: @Composable (Long) -> Unit,
) {

    navigation(
        startDestination = ScreenRoute.TrackApiList.route,
        route = ScreenRoute.TrackApi.route,
    ) {

        composable(ScreenRoute.TrackApiList.route) {
            trackApiListContent()
        }
        composable(ScreenRoute.PlayTrack.route) {
            val trackId = it.arguments?.getString("track_id")?.toLong() ?: 0L
            playTrackContent(trackId)
        }
    }
}