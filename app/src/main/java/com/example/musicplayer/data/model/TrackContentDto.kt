package com.example.musicplayer.data.model

import com.google.gson.annotations.SerializedName

data class ChartContentDto(
    @SerializedName("tracks") val tracksContentDto : TracksContentDto
)
