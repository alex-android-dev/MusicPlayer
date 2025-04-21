package com.example.presentation.track_screen.media_player.media_service.models

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.State
import androidx.compose.runtime.asIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.media3.common.Player

internal fun Player.state(): PlayerState {
    return PlayerStateImpl(this)
}

internal interface PlayerState {
    val player: Player

    val mediaItemIndex: Int

    val isNextTrackAvailable: Boolean

    val isPause: Boolean

    val maxProgressPosition: State<Int>

    val currentProgressPosition: State<Int>

    fun dispose()
}

internal class PlayerStateImpl(
    override val player: Player
) : PlayerState {
    override var mediaItemIndex: Int by mutableIntStateOf(player.currentMediaItemIndex)
        private set
    override var isNextTrackAvailable: Boolean by mutableStateOf(player.hasNextMediaItem())
        private set
    override var isPause: Boolean by mutableStateOf(!player.isPlaying)
        private set
    private val _maxProgressPosition = mutableIntStateOf(0)
    override var maxProgressPosition: State<Int> = _maxProgressPosition.asIntState()
        private set
    private val _currentProgressPosition = mutableIntStateOf(0)
    override var currentProgressPosition: State<Int> = _currentProgressPosition.asIntState()
        private set

    private val handler = Handler(Looper.getMainLooper())
    private val updateProgressAction = object : Runnable {
        override fun run() {
            if (player.isPlaying) {
                _maxProgressPosition.value = player.duration.div(1000).toInt()
                _currentProgressPosition.value = player.currentPosition.div(1000).toInt()
            }
            handler.postDelayed(this, 1000)
        }
    }


    private fun startProgressUpdates() {
        handler.post(updateProgressAction)
    }

    private fun stopProgressUpdates() {
        handler.removeCallbacks(updateProgressAction)
    }


    private val listener = object : Player.Listener {
        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            mediaItemIndex = player.currentMediaItemIndex
            isNextTrackAvailable = player.hasNextMediaItem()
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            isPause = !isPlaying
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_ENDED, Player.STATE_IDLE -> stopProgressUpdates()
            }
        }

    }

    init {
        player.addListener(listener)
        startProgressUpdates()
    }

    override fun dispose() {
        player.removeListener(listener)
        stopProgressUpdates()
    }
}