package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class ChartContentDto(
    @SerializedName("tracks") val tracksContentDto : TracksContentDto
)
