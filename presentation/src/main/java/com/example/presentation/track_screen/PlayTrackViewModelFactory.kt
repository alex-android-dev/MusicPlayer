package com.example.presentation.track_screen

import androidx.annotation.OptIn
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.UnstableApi
import com.example.domain.interactors.PlayTrackInteractor

@Suppress("UNCHECKED_CAST")
class PlayTrackViewModelFactory(
    private val musicPlayerInteractor: PlayTrackInteractor,
) : ViewModelProvider.Factory {

    @OptIn(UnstableApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayTrackViewModel(
            musicPlayerInteractor,
        ) as T
    }

}