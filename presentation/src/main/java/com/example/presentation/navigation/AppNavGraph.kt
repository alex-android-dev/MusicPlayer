package com.example.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    trackListApiContent: @Composable () -> Unit,
    trackListDownloadedContent: @Composable () -> Unit,
) {

    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.TrackListApi.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {

        // Вызов экрана треков по API
        composable(
            route = ScreenRoute.TrackListApi.route,
        ) {
            trackListApiContent()
        }

        // Вызов экрана загруженных треков
        composable(
            route = ScreenRoute.TrackListDownloaded.route,
        ) {
            trackListDownloadedContent()
        }

    }

}