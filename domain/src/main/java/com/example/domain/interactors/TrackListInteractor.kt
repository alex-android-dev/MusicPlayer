package com.example.domain.interactors

import com.example.domain.TrackListState
import com.example.domain.TrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TrackListInteractor(
    private val trackRepository: TrackRepository
) {
    private val backTrackListStatus = MutableStateFlow<TrackListState>(TrackListState.Initial)
    val trackListStatus = backTrackListStatus.asStateFlow()


    /** Загрузка треков **/
    suspend fun loadTrackList() {
        backTrackListStatus.emit(TrackListState.Loading)

        val result = trackRepository.getTrackList()

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackListStatus.emit(TrackListState.Loaded(it)) }
        } else {
            result.exceptionOrNull()?.message?.let { backTrackListStatus.emit(TrackListState.Failed(msg = it)) }
        }
    }

}