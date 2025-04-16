package com.example.musicplayer.data.model

import com.google.gson.annotations.SerializedName

data class AlbumDto(
    @SerializedName("id") val id : Long,
    @SerializedName("cover_big") val cover : String,
)
