package com.example.domain

import com.example.domain.entities.Track
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Репозиторий для управления логикой работы
 */

interface TrackRepository {
    /**
     * Result - успех / не успех
     */
    suspend fun getTrackList(): Result<List<Track>>

    /* TODO
    suspend fun getTracksByAlbum(id: Long): Pair<Boolean, List<Track>>=
    suspend fun findTrackByName(name: String): Pair<Boolean, Track>
    suspend fun getTrackById(id: Long): Pair<Boolean, Track>

     */
}