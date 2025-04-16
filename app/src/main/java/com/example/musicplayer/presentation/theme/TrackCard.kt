package com.example.musicplayer.presentation.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplayer.R
import com.example.musicplayer.domain.Entities.Track

@Composable
fun TrackCard(track: Track) {

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize(),
            painter = painterResource(track.defaultCoverResId),
            contentDescription = null // todo установить
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
        ) {
            Text(track.author)
            Spacer(modifier = Modifier.height(2.dp))
            Text(track.name)
        }
    }

}

// todo настроить цвета текста в зависимости от цвета

@Composable
@Preview
fun TrackCardPreview() {
    val track = Track(
        name = "Game is Over",
        author = "Metallica",
        defaultCoverResId = R.drawable.cover_test,
        coverUrl = "",
        id = 0,
        compositionUrl = "",
    )

    TrackCard(track)

}
