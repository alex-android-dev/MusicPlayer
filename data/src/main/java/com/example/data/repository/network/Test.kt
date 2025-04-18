package com.example.data.repository.network

import com.example.data.repository.mapper.Mapper


suspend fun main() {

    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    val content = apiService.loadChartTracks()

    val list =
        mapper.mapChartContentToTrackList(content)

    println(list)


}