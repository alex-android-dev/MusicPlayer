package com.example.presentation.track_screen

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.UnstableApi
import com.example.domain.TrackListState
import com.example.domain.entities.Track
import com.example.domain.interactors.PlayTrackInteractor
import com.example.presentation.track_screen.components.PlayerComponent
import com.example.presentation.track_screen.components.TopBar
import com.example.presentation.track_screen.components.TrackInfoComponent
import com.example.presentation.track_screen.media_player.media_service.states.MediaStateEvents

@OptIn(UnstableApi::class)
@Composable
fun PlayTrackScreen(
    interactor: PlayTrackInteractor,
    trackId: Long,
    onBackPressed: () -> Unit,
) {

    val viewModel: PlayTrackViewModel = viewModel(
        factory = PlayTrackViewModelFactory(interactor, savedStateHandle =)
    )

    val musciServiceState = viewModel.musicServiceHandler?.musicStates?.collectAsState()

    val trackState = viewModel.currentSelectedTrackFromInteractor.collectAsState()

    val trackListState = viewModel.currentTrackList.collectAsState(TrackListState.Initial)

    viewModel.getTrackById(trackId)
    viewModel.getAlbumByTrackId(trackId)

    var trackProgress by remember { mutableFloatStateOf(0f) }
    var stateIsTrackPlaying by remember { mutableStateOf(true) }

    Log.d("PlayTrackScreen", "trackId: $trackId")
    Log.d("PlayTrackScreen", "trackId: ${trackState.value}")

    when (val trackListState = trackListState.value) {
        is TrackListState.Loaded -> {
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
                Column(
                    modifier = Modifier.padding(padding)
                ) {
                    TrackInfoComponent(
                        track = trackState
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    PlayerComponent(
                        progress = trackProgress,
                        onProgressCallback = {
                            trackProgress = it
                        },
                        isMusicPlaying = stateIsTrackPlaying,
                        onPreviousCallback = {
                            // TODO
                        },
                        onStartCallback = {
                            musciServiceState?.value == MediaStateEvents.PlayPause
                            stateIsTrackPlaying = !stateIsTrackPlaying
                        },
                        onNextCallback = {
                            // TODO
                        },
                    )
                }
            }
        }
    }

}


//
//@Preview
//@Composable
//fun TrackScreenPreview() {
//    Scaffold { padding ->
//        padding
//        PlayTrackScreen(
//            onBackPressed = {},
//            trackId = 0L,
//            viewModel = ,
//        )
//    }
//
//}