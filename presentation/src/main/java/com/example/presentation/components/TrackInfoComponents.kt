package com.example.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.entities.Track
import com.example.presentation.R

@Composable
internal fun TrackInfoComponents(
    track: Track?
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val icStubCover = R.drawable.ic_stub_cover

        Surface(shape = MaterialTheme.shapes.large) {
            AsyncImage(
                modifier = Modifier.size(350.dp),
                model = track?.coverUrl,
                contentDescription = null,
                error = painterResource(icStubCover),
                placeholder = painterResource(icStubCover),
                fallback = painterResource(icStubCover),
            )
        }

        track?.let {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    track.compositionName,
                )
                Text(
                    track.albumName
                )
                Text(
                    track.authorName,
                )
            }
        }

    }

}


@Composable
@Preview
private fun CurrentTrackInfoPreview(
) {

    val track = Track(
        id = 0,
        compositionName = "Composition Name",
        albumName = "Album Name",
        authorName = "Author Name",
        coverUrl = "",
        compositionUrl = ""
    )

    TrackInfoComponents(
        track = track
    )
}