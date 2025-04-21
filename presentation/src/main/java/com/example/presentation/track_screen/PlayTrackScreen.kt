package com.example.presentation.track_screen

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaController
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.PlayTrackInteractor
import com.example.presentation.components.FailedScreen
import com.example.presentation.components.LoadingScreen
import com.example.presentation.track_screen.components.PlayerComponent
import com.example.presentation.track_screen.components.TopBar
import com.example.presentation.track_screen.components.TrackInfoComponent
import com.example.presentation.track_screen.media_player.media_service.models.MediaControllerManager
import com.example.presentation.track_screen.media_player.media_service.models.state

@OptIn(UnstableApi::class)
@Composable
fun PlayTrackScreen(
    interactor: PlayTrackInteractor,
    trackId: Long,
    onBackPressed: () -> Unit,
) {
    val viewModel: PlayTrackViewModel = viewModel(
        factory = PlayTrackViewModelFactory(interactor)
    )

    /** Для обрабокти событий UI **/
    val controllerManager by rememberManagedMediaController()

    val playerState = remember(key1 = controllerManager) { controllerManager?.state() }

    val trackState = viewModel.trackState.collectAsState()
    val trackListState = viewModel.trackListState.collectAsState(TrackListState.Initial)


    LaunchedEffect(trackId) {
        viewModel.getTrackById(trackId)
        viewModel.getAlbumByTrackId(trackId)
        Log.d("PlayTrackScreen", "load by id: $trackId")
    }

    when (val state = trackListState.value) {
        is TrackListState.Loaded -> {
            DisposableEffect(Unit) {
                controllerManager?.let {
                    it.setMediaItems(state.trackList.toMediaItem())
                    it.prepare()
                    it.playWhenReady = true
                }

                onDispose {
                    playerState?.dispose()
                }
            }

            LaunchedEffect(playerState?.mediaItemIndex) {
                playerState?.run {
                    val currentTrack = state.trackList.getOrNull(playerState.mediaItemIndex)

                    currentTrack?.let {
                        viewModel.getTrackById(currentTrack.id)
                    }
                }
            }

            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    TopBar(
                        imgVector = Icons.AutoMirrored.Filled.ArrowBack,
                        onIconClickListener = { onBackPressed() },
                        text = "Media Player"
                    )
                }
            ) { padding ->
                val stateTrackPaused = remember { mutableStateOf(true) }

                when (val trackState = trackState.value) {
                    is Track -> {
                        Column(
                            modifier = Modifier.padding(padding)
                        ) {
                            TrackInfoComponent(
                                track = trackState
                            )

                            Spacer(modifier = Modifier.height(10.dp))
                            var isPaused by rememberSaveable { mutableStateOf(false) }

                            controllerManager?.let { contorller ->
                                playerState?.let { player ->
                                    PlayerComponent(
                                        progress = player.currentProgressPosition.value.toFloat(),
                                        onProgressCallback = {
                                            contorller.seekTo(it.times(1000L).toLong())
                                        },
                                        isMusicPlaying = isPaused,
                                        onPreviousCallback = {
                                            contorller.seekToPrevious()
                                        },
                                        onStartCallback = {
                                            if (isPaused == true)
                                                player.player.play()
                                            else player.player.pause()
                                            isPaused = !isPaused
                                        },
                                        onNextCallback = {
                                            controllerManager?.seekToNext()
                                        },
                                        durationTrack = player.maxProgressPosition.value.toFloat(),
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }

        is TrackListState.Loading -> {
            LoadingScreen()
        }

        is TrackListState.Failed -> {
            FailedScreen()
        }

        TrackListState.Initial -> {}
    }

}

fun List<Track>.toMediaItem(): List<MediaItem> {
    return this.map { trackItem ->
        MediaItem.Builder()
            .setUri(trackItem.compositionUrl)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setAlbumArtist(trackItem.authorName)
                    .setDisplayTitle(trackItem.compositionName)
                    .setSubtitle(trackItem.albumName)
                    .build()
            ).build()
    }
}

/**
 * Отдает в MediaController как State
 * */
@Composable
fun rememberManagedMediaController(
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle
): State<MediaController?> {
    // Application context is used to prevent memory leaks
    val appContext = LocalContext.current.applicationContext
    val controllerManager = remember { MediaControllerManager.getInstance(appContext) }

    // Observe the lifecycle to initialize and release the MediaController at the appropriate times.
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> controllerManager.initialize()
                Lifecycle.Event.ON_STOP -> controllerManager.release()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    return controllerManager.controller
}