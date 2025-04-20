package com.example.presentation.track_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.interactors.TrackListInteractor

class TrackListViewModelFactory(
    private val trackListInteractor: TrackListInteractor,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TrackListViewModel(trackListInteractor) as T
    }
}