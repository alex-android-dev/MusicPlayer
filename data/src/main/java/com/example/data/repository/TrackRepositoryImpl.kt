package com.example.data.repository

import com.example.data.repository.mapper.Mapper
import com.example.data.repository.network.ApiFactory
import com.example.data.repository.network.ApiService
import com.example.domain.TrackRepository
import com.example.domain.entities.Track

class TrackRepositoryImpl() : TrackRepository {
    private val mapper = Mapper()
    private val apiService = ApiFactory.apiService

    override suspend fun getTrackList(): Pair<Boolean, List<Track>> {
        return try {
            val response = apiService.loadChartTracks()
            val tracks = mapper.mapChartContentToTrackList(response)
            Pair(true, tracks)
        } catch (e: Exception) {
            Pair(false, listOf()) // TODO временная заглушка
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