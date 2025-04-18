package com.example.presentation.presentation.TrackListScreen

import androidx.lifecycle.ViewModel
import com.example.domain.TrackRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class TrackListViewModel(
    private val repository: TrackRepository
) : ViewModel() {

    val

    val screenState = repository
        .tracks
        .filter {
            it.isNotEmpty()
        }
        .map {
            TrackListScreenState.Tracks(it) as TrackListScreenState
        }
        .onStart {
            emit(TrackListScreenState.Loading)
        }
}