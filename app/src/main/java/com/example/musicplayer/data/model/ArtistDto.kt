package com.example.musicplayer.data.model

import com.google.gson.annotations.SerializedName

data class ArtistDto(
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
)
