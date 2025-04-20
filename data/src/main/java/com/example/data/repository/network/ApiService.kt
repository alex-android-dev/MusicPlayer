package com.example.data.repository.network

import com.example.data.repository.model.ResponseContentDto
import com.example.data.repository.model.TrackDto
import com.example.data.repository.model.TracksContentDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


internal interface ApiService {

    @GET("chart")
    suspend fun loadChartTracks(): ResponseContentDto

    @GET("search")
    suspend fun loadTrackByName(@Query("q") name: String): TracksContentDto

    @GET("track/{id}")
    suspend fun loadTrackById(@Path("id") id: String): TrackDto

    @GET("album/{id}")
    suspend fun loadTrackListByAlbum(@Path("id") id: String): ResponseContentDto

}