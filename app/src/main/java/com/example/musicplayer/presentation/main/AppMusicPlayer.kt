package com.example.musicplayer.presentation.main

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.musicplayer.domain.Entities.Track
import kotlinx.coroutines.flow.MutableStateFlow

object AppMusicPlayer {
    private var _player: ExoPlayer? = null

    fun initPLayer(context: Context) {
        if (_player == null) _player = ExoPlayer.Builder(context).build()
    }

    fun getPlayer(): ExoPlayer = _player ?: throw IllegalStateException("Player not initialized")

    fun playTestTrack() {
        if (_player == null) return
        val uri =
            "https://cdnt-preview.dzcdn.net/api/1/1/5/e/5/0/5e54e56f3403918351900ae8dd1fadf5.mp3?hdnea=exp=1744924359~acl=/api/1/1/5/e/5/0/5e54e56f3403918351900ae8dd1fadf5.mp3*~data=user_id=0,application_id=42~hmac=e6f9f46cd4113a3ff7cb9ac7a8c18e01130df828c7dc2b828d61a575e7dcba7c"
        val mediaItem = MediaItem.fromUri(uri)

        _player?.apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    fun playTrackList(uriList: List<Track>) {
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

    fun play() {
        _player?.play()
    }

    fun pause() {
        _player?.pause()
    }

    fun stop() {
        _player?.stop()
    }

    fun releasePlayer() {
        if (_player == null) return

        _player?.release()
    }
}