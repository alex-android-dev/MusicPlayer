package com.example.data.repository

import android.util.Log
import com.example.data.repository.local.ContentResolverHelper
import com.example.domain.TrackRepository
import com.example.domain.entities.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImpl(
    private val contentResolverHelper: ContentResolverHelper,
) : TrackRepository {

    var localTrackList = listOf<Track>()

    override suspend fun getChartTrackList(): Result<List<Track>> = withContext(Dispatchers.IO) {
        localTrackList = contentResolverHelper.getAudioData()
        Log.d("LocalRepositoryImpl", "${localTrackList}")
        Result.success(localTrackList)
    }

    override suspend fun getTracksByAlbumId(id: Long): Result<List<Track>> {
        return Result.success(localTrackList)
    }

    override suspend fun getTracksByName(name: String): Result<List<Track>> {
        val filterTrackList = localTrackList.filter { it.compositionName == name }
        return Result.success(filterTrackList)
    }

    override suspend fun getTrackById(id: Long): Result<Track> {
        val track = localTrackList.find { it.id == id }

        if (track == null) return throwNotFoundTrackError()

        return Result.success(track)
    }
}

private fun throwNotFoundTrackError() =
    Result.failure<Track>(Exception("Track are not found"))