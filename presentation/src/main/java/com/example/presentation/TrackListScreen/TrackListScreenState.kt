package com.example.presentation.presentation.TrackListScreen

import com.example.musicplayer.domain.Entities.Track

sealed class TrackListScreenState {

    object Initial : TrackListScreenState()
    object Loading : TrackListScreenState()

    data class Tracks(
        val trackList: List<Track>
    ) : TrackListScreenState()

}