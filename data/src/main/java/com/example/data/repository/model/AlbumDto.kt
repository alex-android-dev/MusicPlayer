package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class AlbumDto(
    @SerializedName("id") val id : Long,
    @SerializedName("title") val name : String,
    @SerializedName("cover_big") val cover : String,
)
