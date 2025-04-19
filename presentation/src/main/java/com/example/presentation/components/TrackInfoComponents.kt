package com.example.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                modifier = Modifier.fillMaxWidth(),
                model = track?.coverUrl,
                contentDescription = "song cover", // TODO
                error = painterResource(icStubCover),
                placeholder = painterResource(icStubCover),
                fallback = painterResource(icStubCover),
            )
        }

        Spacer(Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            TrackInfo(
                track?.compositionName ?: "",
            )

            Spacer(Modifier.height(5.dp))

            TrackInfo(
                track?.albumName ?: "",
            )

            Spacer(Modifier.height(5.dp))

            TrackInfo(
                track?.authorName ?: "",
            )
        }
    }

}

@Composable
private fun TrackInfo(str: String) {
    val text = if (str.isEmpty()) ""
    else str


    Text(
        text,
        fontSize = 20.sp
    )
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