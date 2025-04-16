package com.example.musicplayer.data.model

import com.google.gson.annotations.SerializedName

data class TrackDto(
    @SerializedName("id") val id : Long,
    @SerializedName("title") val name : String,
    @SerializedName("preview") val compositionUrl : String,
    @SerializedName("artist") val artist : ArtistDto,
    @SerializedName("album") val album : AlbumDto,
)
