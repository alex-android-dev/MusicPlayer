package com.example.domain

import com.example.domain.entities.Track

/**
 * Репозиторий для управления логикой работы
 */

interface RepositoryTrack {
    /**
     * Boolean - успех / не успех
     */
    suspend fun getTrackList(): Pair<Boolean, List<Track>>
    suspend fun getTracksByAlbum(id: Long): Pair<Boolean, List<Track>>

    suspend fun findTrackByName(name: String): Pair<Boolean, Track>
    suspend fun getTrackById(id: Long): Pair<Boolean, Track>
}