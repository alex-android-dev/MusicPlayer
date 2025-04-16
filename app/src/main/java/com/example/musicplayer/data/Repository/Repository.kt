package com.example.musicplayer.data.Repository

import com.example.musicplayer.data.mapper.Mapper
import com.example.musicplayer.data.network.ApiFactory
import com.example.musicplayer.domain.Entities.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class Repository {
    private val apiService = ApiFactory.apiService
    private val mapper = Mapper()

    private val _trackList = mutableListOf<Track>()
    private val trackList
        get() = _trackList.toList()

    private val scope = CoroutineScope(Dispatchers.Default)

    private val loadedListTracks = flow {
        val response = apiService.loadChartTracks()
        val tracks = mapper.mapChartContentToTrackList(response)

        _trackList.addAll(tracks)
        emit(trackList)
    }

    val tracks: StateFlow<List<Track>> = loadedListTracks.stateIn(
        scope = scope, started = SharingStarted.Lazily, initialValue = trackList
    )

}