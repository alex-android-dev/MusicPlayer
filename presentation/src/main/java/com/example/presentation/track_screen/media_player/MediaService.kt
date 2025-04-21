package com.example.presentation.track_screen.media_player

import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.presentation.track_screen.media_player.media_service.AppPlayer
import com.techullurgy.media3musicplayer.media_player.media_notification.MusicNotificationManager

/** Сервис для прослушивания музыки **/
class MediaService : MediaSessionService() {

    private lateinit var mediaSession: MediaSession
    private lateinit var musicNotificationManager: MusicNotificationManager


    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        Log.d("MediaService", "onCreate")

        AppPlayer.initPlayer(this)

        val player = AppPlayer.getPlayer() ?: return

        mediaSession = MediaSession.Builder(this, player).build()
        musicNotificationManager = MusicNotificationManager(this, player)
    }

    @OptIn(UnstableApi::class)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        musicNotificationManager.startMusicNotificationService(
            mediaSession = mediaSession,
            mediaSessionService = this
        )
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

}