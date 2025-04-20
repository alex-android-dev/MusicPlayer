package com.example.presentation.track_screen.media_player

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.presentation.track_screen.media_player.media_service.states.MediaStateEvents
import com.example.presentation.track_screen.media_player.media_service.states.MusicStates
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/** Класс для управления музыкой через библиотеку ExoPlayer **/
class MusicServiceHandler(
    private val exoPlayer: Player,
) : Player.Listener {

    /** Состояние плеера **/
    private val _musicState: MutableStateFlow<MusicStates> = MutableStateFlow(MusicStates.Initial)
    val musicStates: StateFlow<MusicStates> = _musicState.asStateFlow()

    private var job: Job? = null

    init {
        exoPlayer.addListener(this)
    }

    /** Методы для установки медиа **/
    fun setMediaItem(mediaItem: MediaItem) {
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    fun setMediaItemList(mediaItems: List<MediaItem>) {
        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
    }


    /** Метод обработки событий **/
    suspend fun onMediaStateEvents(
        mediaStateEvents: MediaStateEvents,
        selectedMusicIndex: Int = -1,
        seekPosition: Long = 0,
    ) {
        when (mediaStateEvents) {
            MediaStateEvents.Backward -> exoPlayer.seekBack()
            MediaStateEvents.Forward -> exoPlayer.seekForward()
            MediaStateEvents.PlayPause -> playPauseMusic()
            MediaStateEvents.SeekTo -> exoPlayer.seekTo(seekPosition)
            MediaStateEvents.SeekToNext -> exoPlayer.seekToNext()
            MediaStateEvents.SeekToPrevious -> exoPlayer.seekToPrevious()
            MediaStateEvents.Stop -> stopProgressUpdate()
            /** Определяем какой трек выбран **/
            MediaStateEvents.SelectedMusicChange -> {
                when (selectedMusicIndex) {
                    // Играет текущий трек
                    exoPlayer.currentMediaItemIndex -> {
                        playPauseMusic()
                    }
                    // Выбран другой трек
                    else -> {
                        exoPlayer.seekToDefaultPosition(selectedMusicIndex)
                        _musicState.value = MusicStates.MediaPlaying(
                            isPlaying = true
                        )
                        exoPlayer.playWhenReady = true
                        startProgressUpdate()
                    }
                }
            }

            is MediaStateEvents.MediaProgress -> {
                exoPlayer.seekTo(
                    (exoPlayer.duration * mediaStateEvents.progress).toLong()
                )
            }
        }
    }

    /** Обрабатываем события от ExoPlayer, связываем их с musicState **/
    override fun onPlaybackStateChanged(playbackState: Int) {
        when (playbackState) {
            ExoPlayer.STATE_BUFFERING ->
                _musicState.value = MusicStates.MediaBuffering(exoPlayer.currentPosition)

            ExoPlayer.STATE_READY ->
                _musicState.value = MusicStates.MediaReady(exoPlayer.duration)

            Player.STATE_ENDED -> {}

            Player.STATE_IDLE -> {}
        }
    }

    /** Связываем статус работы плеера с musicState **/
    @OptIn(DelicateCoroutinesApi::class)
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _musicState.value = MusicStates.MediaPlaying(isPlaying = isPlaying)
        _musicState.value = MusicStates.CurrentMediaPlaying(exoPlayer.currentMediaItemIndex)
        if (isPlaying) {
            GlobalScope.launch(Dispatchers.Main) {
                startProgressUpdate()
            }
        } else {
            stopProgressUpdate()
        }
    }

    /** Связываем статус работы плеера с musicState **/
    private suspend fun playPauseMusic() {
        if (exoPlayer.isPlaying) {
            exoPlayer.pause()
            stopProgressUpdate()
        } else {
            exoPlayer.play()
            _musicState.value = MusicStates.MediaPlaying(
                isPlaying = true
            )
            startProgressUpdate()
        }
    }

    /** Обновляет прогресс трека **/
    private suspend fun startProgressUpdate() = job.run {
        while (true) {
            delay(500)
            _musicState.value = MusicStates.MediaProgress(exoPlayer.currentPosition)
        }
    }

    private fun stopProgressUpdate() {
        job?.cancel()
        _musicState.value = MusicStates.MediaPlaying(isPlaying = false)
    }
}