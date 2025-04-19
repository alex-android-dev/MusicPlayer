package com.example.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.entities.Track
import com.example.presentation.R

@Composable
fun PLayerScreen(
    musicItem: Track,
    progress: Float,
    onProgressCallback: (Float) -> Unit,
    isMusicPlaying: Boolean,
    onStartCallback: () -> Unit,
    onNextCallback: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AuthorInfo(
                track = musicItem,
                modifier = Modifier.weight(1f)
            )
            MiniPlayerControls(
                isMusicPlaying = isMusicPlaying,
                onStartCallback = onStartCallback,
                onNextCallback = onNextCallback
            )
        }
        Slider(
            value = progress,
            valueRange = 0f..100f,
            onValueChange = {
                onProgressCallback(it)
            }
        )
    }
}

@Composable
private fun MiniPlayerControls(
    modifier: Modifier = Modifier,
    isMusicPlaying: Boolean,
    onStartCallback: () -> Unit,
    onNextCallback: () -> Unit,
) {
    Row(
        modifier = modifier
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlayerIcon(
            icon = (if (isMusicPlaying) {
                ImageVector.vectorResource(id = R.drawable.ic_pause)
            } else {
                ImageVector.vectorResource(id = R.drawable.ic_play)
            })
        )
        {
            onStartCallback()
        }
    }

    Icon(
        painter = painterResource(R.drawable.ic_skip_next),
        contentDescription = "next",
        modifier = Modifier
            .clickable {
                onNextCallback()
            }
    )

}

@Composable
private fun AuthorInfo(
    track: Track,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (track.compositionUrl != null) {
            AsyncImage(
                model = track.compositionUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
        } else {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_music_note),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = track.compositionName,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
            Text(
                text = track.authorName,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Clip,
                maxLines = 1
            )
        }
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
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = icon, contentDescription = "player icon")
    }
}

@Preview
@Composable
private fun PreviewMiniPlayer() {
    MaterialTheme {
        Surface(Modifier.background(MaterialTheme.colorScheme.background)) {

            val track = Track(
                id = 0,
                compositionName = "Composition Name",
                authorName = "Author Name",
                coverUrl = "",
                compositionUrl = ""
            )

            PLayerScreen(
                musicItem = track,
                progress = 0.1f,
                onProgressCallback = {},
                isMusicPlaying = false,
                onStartCallback = {},
                onNextCallback = {}
            )

        }
    }
}
