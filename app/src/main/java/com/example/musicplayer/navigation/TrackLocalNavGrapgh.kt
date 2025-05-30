package com.example.musicplayer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.trackLocalNavGraph(
    trackLocalListContent: @Composable () -> Unit,
    playTrackContent: @Composable (Long) -> Unit,
) {

    navigation(
        startDestination = ScreenRoute.TrackLocalList.route,
        route = ScreenRoute.TrackLocal.route,
    ) {
        composable(ScreenRoute.TrackLocalList.route) {
            trackLocalListContent()
        }

        composable(ScreenRoute.PlayTrack.route) {
            val trackId = it.arguments?.getString("track_id")?.toLong() ?: 0L
            playTrackContent(trackId)
        }
    }
}