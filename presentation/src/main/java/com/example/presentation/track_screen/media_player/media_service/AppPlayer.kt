package com.example.presentation.track_screen.media_player.media_service

import android.content.Context
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector

@UnstableApi
object AppPlayer {
    private var player: ExoPlayer? = null

    fun initPlayer(context: Context) {
        if (player != null) return
        player = ExoPlayer.Builder(context)
            .setAudioAttributes(this.attributes, true)
            .setHandleAudioBecomingNoisy(true)
            .setTrackSelector(DefaultTrackSelector(context))
            .build()
        Log.d("AppPlayer", "appPlayer: init player")
    }

    fun getPlayer(): ExoPlayer? = player

    fun release() {
        player?.release()
        player = null
    }

    private val attributes =
        AudioAttributes.Builder().setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA).build()
}