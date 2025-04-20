package com.example.presentation.track_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.domain.entities.Track
import com.example.presentation.track_screen.components.PlayerComponent
import com.example.presentation.track_screen.components.TrackInfoComponent

@Composable
fun TrackScreen(
    trackId: Long,
) {
    val trackProgress = remember { mutableFloatStateOf(0f) }
    val stateIsTrackPlaying = remember { mutableStateOf(true) }


    Text(trackId.toString())

    Column {
        TrackInfoComponent(
            track = null
        )

        Spacer(modifier = Modifier.height(10.dp))

        // todo
        PlayerComponent(
            progress = trackProgress.value,
            onProgressCallback = {
                trackProgress.value = it
            },
            isMusicPlaying = stateIsTrackPlaying.value,
            onPreviousCallback = {}, //
            onStartCallback = {
                stateIsTrackPlaying.value = !stateIsTrackPlaying.value
            },
            onNextCallback = {},
        )
    }


}


@Preview
@Composable
fun TrackScreenPreview() {
    TrackScreen(0L)
}