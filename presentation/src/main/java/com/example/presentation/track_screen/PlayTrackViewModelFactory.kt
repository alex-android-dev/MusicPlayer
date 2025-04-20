package com.example.presentation.track_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.MusicPlayerInteractor

class PlayTrackViewModelFactory(
    private val musicPlayerInteractor: MusicPlayerInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlayTrackViewModel(musicPlayerInteractor) as T
    }
}