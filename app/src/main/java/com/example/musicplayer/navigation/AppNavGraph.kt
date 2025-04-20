package com.example.musicplayer.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    trackApiListContent: @Composable () -> Unit,
    trackLocalListContent: @Composable () -> Unit,
    playTrackContent: @Composable (Long) -> Unit, // TODO подумать что сюда передавать. album id
) {

    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.TrackApi.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {

        trackApiNavGraph(
            trackApiListContent = trackApiListContent,
            playTrackContent = playTrackContent
        )

        trackLocalNavGraph(
            trackLocalListContent = trackLocalListContent,
            playTrackContent = playTrackContent
        )


    }

}