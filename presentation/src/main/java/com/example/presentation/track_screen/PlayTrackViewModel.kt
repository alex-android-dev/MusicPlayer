package com.example.presentation.track_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.PlayTrackInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlayTrackViewModel(
    private val musicPlayerInteractor: PlayTrackInteractor
) : ViewModel() {

    val currentTrack = musicPlayerInteractor.trackState
        .map {
            Track(
                id = it?.id ?: 0,
                compositionName = it?.compositionName ?: "",
                albumName = it?.albumName ?: "",
                albumId = it?.albumId ?: 0,
                authorName = it?.authorName ?: "",
                coverUrl = it?.coverUrl ?: "",
                compositionUrl = it?.compositionUrl ?: ""
            )
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

