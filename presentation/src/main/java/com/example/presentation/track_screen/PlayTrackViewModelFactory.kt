package com.example.presentation.track_screen

import androidx.annotation.OptIn
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.example.domain.interactors.PlayTrackInteractor

class PlayTrackViewModelFactory(
    private val musicPlayerInteractor: PlayTrackInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModelProvider.Factory {

    @OptIn(UnstableApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayTrackViewModel(
            musicPlayerInteractor,
            savedStateHandle
        ) as T
    }
}