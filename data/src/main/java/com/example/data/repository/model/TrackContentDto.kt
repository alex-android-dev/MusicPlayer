package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class ResponseContentDto(
    @SerializedName("tracks") val tracksContentDto : TracksContentDto
)
