package com.example.presentation.track_list

/*
class TrackListViewModel(
    private val repository: TrackRepository
) : ViewModel() {

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

 */