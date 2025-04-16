package com.example.musicplayer.data.network

import com.example.musicplayer.data.model.ChartContentDto
import com.example.musicplayer.data.model.TracksContentDto
import retrofit2.http.GET

private const val CHART = "chart"

interface ApiService {

    @GET(CHART)
    suspend fun loadChartTracks(): ChartContentDto

}