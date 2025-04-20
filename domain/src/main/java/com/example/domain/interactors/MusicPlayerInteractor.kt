package com.example.domain.interactors

import com.example.domain.TrackListState
import com.example.domain.TrackRepository
import com.example.domain.entities.Track
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** Интерактор - отдает список треков **/
class MusicPlayerInteractor(
    private val trackRepository: TrackRepository
) {

    /** стейт трек листа **/
    private val backTrackListState = MutableStateFlow<TrackListState>(TrackListState.Initial)
    val trackListState = backTrackListState.asStateFlow()

    /** стейт текущего трека **/
    private val backTrackState = MutableStateFlow<Track?>(null)
    val trackState = backTrackState.asStateFlow()

    suspend fun getTrackById(id: Long) {
        val result = trackRepository.getTrackById(id)

        if (result.isSuccess) {
            result.getOrNull()?.let { backTrackState.emit(it) }
        } else {
            result.exceptionOrNull()?.message?.let {
                backTrackListState.emit(TrackListState.Failed(it))
            }
        }
    }

    suspend fun getAlbumByTrackId(id: Long) {
        backTrackListState.emit(TrackListState.Loading)

        val track = trackRepository.getTrackById(id)

        if (track.isSuccess) {
            track.getOrNull()?.let { track ->
                val albumTrackList = trackRepository.getTracksByAlbumId(track.albumId)

                if (albumTrackList.isSuccess) {
                    albumTrackList.getOrNull()?.let { trackList ->
                        val trackIndex = trackList.indexOfFirst { it.id == track.id }
                        val trackList = trackList.toMutableList()
                        trackList.swap(trackIndex, 0)
                        backTrackListState.emit(TrackListState.Loaded(trackList))
                    }
                }
            }
        } else {
            track.exceptionOrNull()?.message?.let {
                backTrackListState.emit(TrackListState.Failed(it))
            }
        }
    }
}

fun <T> MutableList<T>.swap(index1: Int, index2: Int) {
    if (index1 == index2) return
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}