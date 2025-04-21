package com.example.presentation.track_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.presentation.R

@Composable
fun PlayerComponent(
    progress: Float,
    durationTrack: Float,
    onProgressCallback: (Float) -> Unit,
    isMusicPlaying: Boolean,
    onPreviousCallback: () -> Unit,
    onStartCallback: () -> Unit,
    onNextCallback: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {

        PlayerControls(
            modifier = Modifier.fillMaxWidth(),
            isPaused = isMusicPlaying,
            onStartCallback = onStartCallback,
            onPreviousCallback = onPreviousCallback,
            onNextCallback = onNextCallback,
        )

        Slider(
            value = progress,
            valueRange = 0f..durationTrack,
            onValueChange = {
                onProgressCallback(it)
            }
        )
    }
}

@Composable
private fun PlayerControls(
    modifier: Modifier = Modifier,
    isPaused: Boolean,
    onPreviousCallback: () -> Unit,
    onStartCallback: () -> Unit,
    onNextCallback: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_return_previously),
            contentDescription = "previous",
            modifier = Modifier
                .clickable {
                    onPreviousCallback()
                }
                .size(50.dp)
        )

        PlayerIcon(
            icon = if (isPaused) {
                ImageVector.vectorResource(id = R.drawable.ic_play)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_pause)
            }
        )
        {
            onStartCallback()
        }

        Icon(
            painter = painterResource(R.drawable.ic_skip_next),
            contentDescription = "next",
            modifier = Modifier
                .clickable {
                    onNextCallback()
                }
                .size(50.dp)
        )
    }


}

@Composable
private fun PlayerIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClickCallback: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .clickable {
                onClickCallback()
            }
            .size(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "player icon",
            modifier = modifier.fillMaxSize()
        )
    }
}
