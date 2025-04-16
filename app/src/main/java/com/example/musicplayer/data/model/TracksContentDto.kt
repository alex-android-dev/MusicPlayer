package com.example.musicplayer.data.model

import com.google.gson.annotations.SerializedName

data class TracksContentDto(
    @SerializedName("data") val trackListDto : List<TrackDto>
)

