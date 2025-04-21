package com.example.domain.interactors

import com.example.domain.TrackListState
import com.example.domain.TrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Интерактор для получения стейта и обработки ошибок **/
class TrackListInteractor(
    private val trackRepository: TrackRepository
) {
    private val backTrackListState = MutableStateFlow<TrackListState>(TrackListState.Initial)
    val trackListState = backTrackListState.asStateFlow()


    /** Загрузка чарт треков **/
    suspend fun loadTrackList() {
        backTrackListState.emit(TrackListState.Loading)

        val result = trackRepository.getChartTrackList()

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackListState.emit(TrackListState.Loaded(it)) }
        } else {
            result.exceptionOrNull()?.message?.let {
                backTrackListState.emit(TrackListState.Failed(it))
            }
        }
    }

    /** Загрузка треков по имени трека **/
    suspend fun loadTrackByName(name: String) {
        backTrackListState.emit(TrackListState.Loading)

        val result = trackRepository.getTracksByName(name)

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackListState.emit(TrackListState.Loaded(it)) }
        } else {
            result.exceptionOrNull()?.message?.let {
                backTrackListState.emit(TrackListState.Failed(it))
            }
        }
    }

}