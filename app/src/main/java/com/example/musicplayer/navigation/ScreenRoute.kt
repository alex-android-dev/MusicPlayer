package com.example.musicplayer.navigation

sealed class ScreenRoute(
    val route: String
) {
    /** Граф навигации - экран треков по API **/
    object TrackApi : ScreenRoute(ROUTE_API)
    object TrackApiList : ScreenRoute(ROUTE_TRACK_LIST_API)

    /** Граф навигации - экран локальных треков **/
    object TrackLocal : ScreenRoute(ROUTE_DOWNLOAD)
    object TrackLocalList : ScreenRoute(ROUTE_TRACK_LIST_DOWNLOADED)

    /** Экран с плеером **/
    object PlayTrack : ScreenRoute(ROUTE_PLAY_TRACK)


    private companion object {
        const val ROUTE_API = "track_api"
        const val ROUTE_TRACK_LIST_API = "track_api_list"
        const val ROUTE_DOWNLOAD = "track_download"
        const val ROUTE_TRACK_LIST_DOWNLOADED = "track_list_downloaded"
        const val ROUTE_PLAY_TRACK = "play_track"
    }
}