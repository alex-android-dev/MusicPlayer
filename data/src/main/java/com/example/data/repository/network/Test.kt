package com.example.data.repository.network

import com.example.data.repository.mapper.Mapper
import com.example.domain.entities.Track

suspend fun main() {

    val trackId = 2729273551
    val trackName = "Toxicity"
    val albumId = 566698421

    println(
        testTrackListByAlbum(albumId.toString())
    )
}

private suspend fun testChartResponse(): List<Track> {
    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    val response = apiService.loadChartTracks()
    val trackListChart =
        mapper.mapTrackListDtoToTrackList(response.tracksContentDto.trackListDto)

    return trackListChart
}

private suspend fun testTrackListByName(name: String): List<Track> {
    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    val response = apiService.loadTrackByName(name)
    val trackListByName = mapper.mapTrackListDtoToTrackList(response.trackListDto)

    return trackListByName
}

private suspend fun testTrackById(id: String): Track {
    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    val response = apiService.loadTrackById(id)
    val track = mapper.mapTrackDtoToTrack(response)

    return track
}

private suspend fun testTrackListByAlbum(id: String): List<Track> {
    val apiService = ApiFactory.apiService
    val mapper = Mapper()

    val response = apiService.loadTrackListByAlbum(id)
    val tracks =
        mapper.mapTrackListDtoToTrackList(response.tracksContentDto.trackListDto)

    return tracks
}