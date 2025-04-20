package com.example.presentation.track_screen.media_player

import android.content.Intent
import android.os.Build
import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.techullurgy.media3musicplayer.media_player.media_notification.MusicNotificationManager

/** Сервис для прослушивания музыки **/
class MediaService : MediaSessionService() {

    private lateinit var mediaSession: MediaSession
    private lateinit var musicNotificationManager: MusicNotificationManager


    override fun onCreate() {
        super.onCreate()

        val player = getPlayer()

        mediaSession = MediaSession.Builder(this, player).build()

        musicNotificationManager = MusicNotificationManager(this, player)
    }

    @OptIn(UnstableApi::class)
    private fun getPlayer(): ExoPlayer = ExoPlayer.Builder(this)
        .setAudioAttributes(getAudioAttributesForApp(), true)
        .setHandleAudioBecomingNoisy(true)
        .setTrackSelector(DefaultTrackSelector(this))
        .build()

    @OptIn(UnstableApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            musicNotificationManager.startMusicNotificationService(
                mediaSession = mediaSession,
                mediaSessionService = this
            )
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession =
        mediaSession


    override fun onDestroy() {
        super.onDestroy()
        mediaSession.apply {
            release()
            if (player.playbackState != Player.STATE_IDLE) {
                player.seekTo(0)
                player.playWhenReady = false
                player.stop()
            }
        }
    }

    private fun getAudioAttributesForApp() =
        AudioAttributes.Builder().setContentType(C.AUDIO_CONTENT_TYPE_MUSIC)
            .setUsage(C.USAGE_MEDIA).build()
}