package com.example.musicplayer.data.network

import com.example.musicplayer.data.mapper.Mapper
import com.example.musicplayer.domain.Entities.Track
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

private val dispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()
private val scope = CoroutineScope(dispatcher)

fun main() {

    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    scope.launch {
        val content = apiService.loadChartTracks()

        val list =
            mapper.mapChartContentToTrackList(content)

        println(list)
    }


}