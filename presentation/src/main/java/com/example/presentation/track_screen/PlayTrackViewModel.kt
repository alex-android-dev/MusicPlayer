package com.example.presentation.track_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.PlayTrackInteractor
import com.example.presentation.track_screen.media_player.MusicServiceHandler
import com.example.presentation.track_screen.media_player.media_service.AppPlayer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS

@OptIn(SavedStateHandleSaveableApi::class)
@UnstableApi
class PlayTrackViewModel(
    private val interactor: PlayTrackInteractor,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val musicServiceHandler =
        if (AppPlayer.getPlayer() != null) AppPlayer.getPlayer()
            ?.let { MusicServiceHandler(it) } else null

    init {
        log("appPlayer: ${AppPlayer.getPlayer()}")
        log("music service handler: $musicServiceHandler")
    }

    val currentSelectedTrackFromInteractor = interactor.trackState
        .map {
            Track(
                id = it?.id ?: 0,
                compositionName = it?.compositionName ?: "",
                albumName = it?.albumName ?: "",
                albumId = it?.albumId ?: 0,
                authorName = it?.authorName ?: "",
                coverUrl = it?.coverUrl ?: "",
                compositionUrl = it?.compositionUrl ?: ""
            )
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            TrackListState.Initial
        )

    /** Отадет лист media item и передает в music service handler, если список загружен **/
    val currentTrackList = interactor.trackListState.map { trackListState ->
        when (val tracks = trackListState) {
            is TrackListState.Failed -> trackListState
            is TrackListState.Initial -> trackListState
            is TrackListState.Loading -> trackListState
            is TrackListState.Loaded -> {
                tracks.trackList.map { trackItem ->
                    MediaItem.Builder()
                        .setUri(trackItem.compositionUrl)
                        .setMediaMetadata(
                            MediaMetadata.Builder()
                                .setAlbumArtist(trackItem.authorName)
                                .setDisplayTitle(trackItem.compositionName)
                                .setSubtitle(trackItem.albumName)
                                .build()
                        ).build()
                }.also { itemList ->
                    musicServiceHandler?.setMediaItemList(itemList)
                }
            }
        }
    }

//    /** Стейты для плеера **/
//    var isMusicPlaying by savedStateHandle.saveable { mutableStateOf(false) }
//    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }
//    private var duration by savedStateHandle.saveable { mutableLongStateOf(0L) }
//    private var progressValue by savedStateHandle.saveable { mutableStateOf("00:00") }

    var musicList by mutableStateOf(listOf<Track>())

    var currentSelectedTrack by mutableStateOf(
        Track(
            id = -1,
            compositionName = "",
            albumName = "",
            albumId = -1,
            authorName = "",
            coverUrl = "",
            compositionUrl = ""
        )
    )


    init {
//        viewModelScope.launch {
//            musicServiceHandler?.musicStates?.collectLatest { musicState ->
//                when (val state = musicState) {
//                    is MusicStates.Initial -> {}
//                    is MusicStates.MediaBuffering -> progressCalculation(state.progress)
//                    is MusicStates.MediaPlaying -> isMusicPlaying = state.isPlaying
//                    is MusicStates.MediaProgress -> progressCalculation(state.progress)
//                    is MusicStates.CurrentMediaPlaying ->
//                        currentSelectedTrack = musicList[state.mediaItemIndex]
//
//                    is MusicStates.MediaReady -> {
//                        duration = musicState.duration
//                    }
//                }
//            }
//        }
    }

    private val backUiState =
        MutableStateFlow<TrackListState>(TrackListState.Initial)
    val uiState = backUiState.asStateFlow()


    fun getAlbumByTrackId(id: Long) {
        viewModelScope.launch {
            interactor.getAlbumByTrackId(id)
        }
    }

    fun getTrackById(id: Long) {
        viewModelScope.launch {
            interactor.getTrackById(id)
        }
    }

//    private fun progressCalculation(currentProgress: Long) {
//        progress =
//            if (currentProgress > 0) ((currentProgress.toFloat() / duration.toFloat()) * 100f)
//            else 0f
//
//        progressValue = formatDurationValue(currentProgress)
//    }

    private fun formatDurationValue(duration: Long): String {
        val minutes = MINUTES.convert(duration, MILLISECONDS)
        val seconds = (minutes) - minutes * SECONDS.convert(1, MINUTES)

        return String.format("%02d:%02d", minutes, seconds)
    }

    private fun log(str: String) {
        Log.d("PlayTrackViewModel", str)
    }

}

