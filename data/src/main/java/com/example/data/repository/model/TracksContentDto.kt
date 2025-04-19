package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class TracksContentDto(
    @SerializedName("data") val trackListDto: List<TrackDto>
)

