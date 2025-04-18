package com.example.musicplayer.presentation.TrackListScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musicplayer.data.Repository.Repository
import com.example.musicplayer.domain.Entities.Track
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class TrackListViewModel : ViewModel() {
    private val repository = Repository()

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