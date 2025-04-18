package com.example.data.repository.model

import com.google.gson.annotations.SerializedName

internal data class TrackDto(
    @SerializedName("id") val id : Long,
    @SerializedName("title") val name : String,
    @SerializedName("preview") val compositionUrl : String,
    @SerializedName("artist") val artist : ArtistDto,
    @SerializedName("album") val album : AlbumDto,
)
