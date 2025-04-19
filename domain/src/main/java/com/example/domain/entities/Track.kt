package com.example.domain.entities

data class Track(
    val id: Long,
    val compositionName: String,
    val authorName: String,
    val coverUrl: String,
    val compositionUrl: String,
)