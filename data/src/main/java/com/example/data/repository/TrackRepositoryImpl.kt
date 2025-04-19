package com.example.data.repository

import com.example.data.repository.mapper.Mapper
import com.example.data.repository.model.ChartContentDto
import com.example.data.repository.network.ApiFactory
import com.example.domain.TrackRepository
import com.example.domain.entities.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/** Имплементация репозитория **/
class TrackRepositoryImpl() : TrackRepository {
    private val mapper = Mapper()
    private val apiService = ApiFactory.apiService

    /** Метод возвращает результат в виде List или ошибку **/
    override suspend fun getTrackList(): Result<List<Track>> = withContext(Dispatchers.IO) {
        val response: ChartContentDto? =
            apiService.loadChartTracks()
        if (response == null) return@withContext Result.failure<List<Track>>(Exception("Network error"))

        val trackList = mapper.mapChartContentToTrackList(response)

        return@withContext if (trackList.isEmpty()) {
            Result.failure<List<Track>>(Exception("No tracks found"))
        } else {
            Result.success(trackList)
        }

    }


    /*
        override suspend fun getTracksByAlbum(albomId: Long): Pair<Boolean, List<Track>> {
            TODO("Not yet implemented")
        }

        override suspend fun findTrackByName(name: String): Pair<Boolean, Track> {
            TODO("Not yet implemented")
        }

        override suspend fun getTrackById(id: Long): Pair<Boolean, Track> {
            TODO("Not yet implemented")
        }
    */

}