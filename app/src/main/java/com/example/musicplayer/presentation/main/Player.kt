package com.example.musicplayer.presentation.main

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicplayer.domain.Entities.Track

object Player {
    private var _player: ExoPlayer? = null

    fun initPLayer(context: Context) {
        if (_player == null) _player = ExoPlayer.Builder(context).build()
    }

    fun getPlayer(): ExoPlayer = _player ?: throw IllegalStateException("Player not initialized")

    fun playTracks(uriList: List<Track>) {
        if (_player == null) return

        _player?.apply {
            uriList.forEachIndexed { index, track ->
                val mediaItem = MediaItem.fromUri(track.compositionUrl)

                if (index == 0) {
                    setMediaItem(mediaItem)
                } else {
                    addMediaItem(mediaItem)
                }
            }

            prepare()
            playWhenReady = true
        }
    }

    fun releasePlayer() {
        if (_player == null) return

        _player?.release()
    }


}