package com.example.presentation.track_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.PlayTrackInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS

@OptIn(SavedStateHandleSaveableApi::class)
@UnstableApi
class PlayTrackViewModel(
    private val interactor: PlayTrackInteractor,
) : ViewModel() {

    val trackState = interactor.trackState
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
            TrackListState.Loading
        )

    /** Отдает лист media item и передает в music service handler, если список загружен **/
    val trackListState = interactor.trackListState


    fun getAlbumByTrackId(id: Long) {
        viewModelScope.launch {
            interactor.getAlbumByTrackId(id)
        }
    }

    fun getTrackById(id: Long) {
        viewModelScope.launch {
            interactor.getTrackById(id)
        }
    }


    private fun formatDurationValue(duration: Long): String {
        val minutes = MINUTES.convert(duration, MILLISECONDS)
        val seconds = (minutes) - minutes * SECONDS.convert(1, MINUTES)

        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun log(str: String) {
        Log.d("PlayTrackViewModel", str)
    }

}

