package com.example.data.repository

import com.example.data.repository.mapper.Mapper
import com.example.data.repository.model.ResponseContentDto
import com.example.data.repository.model.TrackDto
import com.example.data.repository.model.TracksContentDto
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
        val response: ResponseContentDto? =
            apiService.loadChartTracks()
        if (response == null) return@withContext throwTracksNetworkError()

        val trackList: List<Track>? =
            mapper.mapTrackListDtoToTrackList(response.tracksContentDto.trackListDto)

        return@withContext if (trackList.isNullOrEmpty()) {
            Result.failure<List<Track>>(Exception("No tracks found"))
        } else {
            Result.success(trackList)
        }
    }

    override suspend fun getTracksByAlbum(id: Long): Result<List<Track>> =
        withContext(Dispatchers.IO) {
            val response: ResponseContentDto? = apiService.loadTrackListByAlbum(id.toString())

            if (response == null) return@withContext throwTracksNetworkError()


            val trackList: List<Track>? =
                mapper.mapTrackListDtoToTrackList(response.tracksContentDto.trackListDto)

            return@withContext if (trackList.isNullOrEmpty()) {
                throwNotFoundTracksError()
            } else {
                Result.success(trackList)
            }
        }


    override suspend fun getTracksByName(name: String): Result<List<Track>> =
        withContext(Dispatchers.IO) {

            val response: TracksContentDto? = apiService.loadTrackByName(name)

            if (response == null) return@withContext throwTracksNetworkError()

            val trackList: List<Track>? = mapper.mapTrackListDtoToTrackList(response.trackListDto)

            return@withContext if (trackList.isNullOrEmpty()) {
                throwNotFoundTracksError()
            } else {
                Result.success(trackList)
            }

        }

    override suspend fun getTrackById(id: Long): Result<Track> = withContext(Dispatchers.IO) {
        val response: TrackDto? = apiService.loadTrackById(id.toString())

        if (response == null) return@withContext throwTrackNetworkError()

        val track: Track? = mapper.mapTrackDtoToTrack(response)

        return@withContext if (track == null) {
            throwNotFoundTrackError()
        } else {
            Result.success(track)
        }
    }
}

private fun throwTracksNetworkError() = Result.failure<List<Track>>(Exception("Network error"))
private fun throwNotFoundTracksError() =
    Result.failure<List<Track>>(Exception("Tracks are not found"))

private fun throwTrackNetworkError() = Result.failure<Track>(Exception("Network error"))
private fun throwNotFoundTrackError() =
    Result.failure<Track>(Exception("Track are not found"))

