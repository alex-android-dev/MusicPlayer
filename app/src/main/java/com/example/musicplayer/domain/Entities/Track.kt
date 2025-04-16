package com.example.musicplayer.domain.Entities

import com.example.musicplayer.R

data class Track(
    val id: Long,
    val name: String,
    val author: String,
    val defaultCoverResId: Int = R.drawable.cover_test,
    val coverUrl: String,
    val compositionUrl: String,
)