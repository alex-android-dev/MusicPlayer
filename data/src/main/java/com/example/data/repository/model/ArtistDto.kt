package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class ArtistDto(
    @SerializedName("id") val id : Long,
    @SerializedName("name") val name : String,
)
