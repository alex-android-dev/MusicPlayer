package com.example.presentation.track_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.PlayTrackInteractor

class PlayTrackViewModelFactory(
    private val musicPlayerInteractor: PlayTrackInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayTrackViewModel(musicPlayerInteractor) as T
    }
}