package com.example.domain

import com.example.domain.entities.Track

/**
 * Репозиторий для управления логикой работы
 */

interface TrackRepository {
    /**
     * Result - успех / не успех
     */

    suspend fun getChartTrackList(): Result<List<Track>>
    suspend fun getTracksByAlbum(id: Long): Result<List<Track>>
    suspend fun getTracksByName(name: String): Result<List<Track>>
    suspend fun getTrackById(id: Long): Result<Track>


}