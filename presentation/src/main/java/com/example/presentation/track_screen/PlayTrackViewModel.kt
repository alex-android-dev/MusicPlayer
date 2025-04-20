package com.example.presentation.track_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.MusicPlayerInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlayTrackViewModel(
    private val musicPlayerInteractor: MusicPlayerInteractor
) : ViewModel() {

    val currentTrack = musicPlayerInteractor.trackState.map {

    }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            TrackListState.Initial
        )

    fun getTrackById(id: Long) {
        viewModelScope.launch {
            musicPlayerInteractor.getTrackById(id)
        }
    }

}

private fun mapToTrack() {

}