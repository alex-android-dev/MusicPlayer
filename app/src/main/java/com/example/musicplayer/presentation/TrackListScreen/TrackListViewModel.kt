package com.example.musicplayer.presentation.TrackListScreen

import androidx.lifecycle.ViewModel
import com.example.musicplayer.data.Repository.RepositoryImpl
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class TrackListViewModel : ViewModel() {
    private val repository = RepositoryImpl()

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