package com.example.domain

import com.example.domain.entities.Track

sealed class TrackListState {

    object Initial : TrackListState()
    object Loading : TrackListState()

    data class Loaded(
        val trackList: List<Track>
    ) : TrackListState()

    data class Failed(
        val msg: String
    ) : TrackListState()
}