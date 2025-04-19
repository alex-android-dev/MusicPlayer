package com.example.musicplayer.navigation

sealed class ScreenRoute(
    val route: String
) {
    object TrackListApi : ScreenRoute(ROUTE_TRACK_LIST_API)
    object TrackListDownloaded : ScreenRoute(ROUTE_TRACK_LIST_DOWNLOADED)


    private companion object {
        const val ROUTE_TRACK_LIST_API = "track_list_api"
        const val ROUTE_TRACK_LIST_DOWNLOADED = "track_list_downloaded"
    }
}