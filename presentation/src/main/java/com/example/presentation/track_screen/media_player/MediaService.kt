package com.example.presentation.track_screen.media_player

import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.CommandButton
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.presentation.R

/** Сервис для прослушивания музыки **/
class MusicService : MediaSessionService() {
    private var mediaSession: MediaSession? = null

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession


    @OptIn(UnstableApi::class)
    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build()

        val nextButton = CommandButton.Builder()
            .setIconResId(R.drawable.ic_skip_next)
            .setPlayerCommand(ExoPlayer.COMMAND_SEEK_TO_NEXT)
            .build()

        mediaSession = MediaSession.Builder(this, player)
            .setCommandButtonsForMediaItems(mutableListOf(nextButton))
            .build()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopSelf()
    }

    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

}