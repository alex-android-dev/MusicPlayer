package com.example.domain.interactors

import com.example.domain.TrackListState
import com.example.domain.TrackRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Интерактор для получения стейта и обработки ошибок **/
class TrackListInteractor(
    private val trackRepository: TrackRepository
) {
    private val backTrackListStatus = MutableStateFlow<TrackListState>(TrackListState.Initial)
    val trackListStatus = backTrackListStatus.asStateFlow()


    /** Загрузка чарт треков **/
    suspend fun loadTrackList() {
        backTrackListStatus.emit(TrackListState.Loading)

        val result = trackRepository.getChartTrackList()

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackListStatus.emit(TrackListState.Loaded(it)) }
        } else {
            result.exceptionOrNull()?.message?.let {
                backTrackListStatus.emit(TrackListState.Failed(it))
            }
        }
    }

    /** Загрузка треков по имени трека **/
    suspend fun loadTrackByName(name: String) {
        backTrackListStatus.emit(TrackListState.Loading)

        val result = trackRepository.getTracksByName(name)

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackListStatus.emit(TrackListState.Loaded(it)) }
        } else {
            result.exceptionOrNull()?.message?.let {
                backTrackListStatus.emit(TrackListState.Failed(it))
            }
        }
    }

}