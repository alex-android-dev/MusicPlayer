package com.example.presentation.track_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.TrackListState
import com.example.domain.interactors.TrackListInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class TrackListViewModel(
    private val trackListInteractor: TrackListInteractor,
) : ViewModel() {

    private val backTrackListStatus = MutableStateFlow<TrackListState>(TrackListState.Initial)

    init {
        loadTrackList()
    }


    /** Получение данных из домейн слоя **/
    val trackListStatus = trackListInteractor.trackListStatus.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        TrackListState.Initial
    )

    fun loadTrackList() = viewModelScope.launch {
        trackListInteractor.loadTrackList()
    }

    companion object {

    }

}