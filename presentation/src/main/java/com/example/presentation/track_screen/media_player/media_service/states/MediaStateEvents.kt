package com.example.presentation.track_screen.media_player.media_service.states

/** управление медиа плеером **/
sealed class MediaStateEvents {
    object PlayPause : MediaStateEvents()
    object SeekToNext : MediaStateEvents()
    object SeekToPrevious : MediaStateEvents()
    object SeekTo : MediaStateEvents()
    object Backward : MediaStateEvents()
    object Forward : MediaStateEvents()
    object Stop : MediaStateEvents()
    object SelectedMusicChange : MediaStateEvents()
    data class MediaProgress(val progress: Float) : MediaStateEvents()
}
