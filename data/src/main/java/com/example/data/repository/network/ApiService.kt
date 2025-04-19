package com.example.data.repository.network

import com.example.data.repository.model.ChartContentDto
import retrofit2.http.GET

private const val CHART = "chart"

internal interface ApiService {

    @GET(CHART)
    suspend fun loadChartTracks(): ChartContentDto

}