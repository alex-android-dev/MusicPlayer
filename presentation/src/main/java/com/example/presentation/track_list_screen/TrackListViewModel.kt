package com.example.presentation.track_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.TrackListState
import com.example.domain.interactors.TrackListInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TrackListViewModel(
    private val trackListInteractor: TrackListInteractor,
) : ViewModel() {

    init {
        loadTrackList()
    }

    /** Получение данных из домейн слоя **/
    val trackListStatus = trackListInteractor.trackListState.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        TrackListState.Initial
    )

    fun loadTrackListByName(name: String) = viewModelScope.launch {
        trackListInteractor.loadTrackByName(name)
    }

    fun loadTrackList() = viewModelScope.launch {
        trackListInteractor.loadTrackList()
    }

}