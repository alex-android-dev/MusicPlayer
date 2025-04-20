package com.example.presentation.presentation.theme

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.domain.entities.Track

@Composable
fun TrackCard(
    track: Track,
    onClickItem: (Long) -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                onClickItem(track.id)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .fillMaxSize(),
            model = track.coverUrl,
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
        ) {
            Text(track.authorName)
            Spacer(modifier = Modifier.height(2.dp))
            Text(track.compositionName)
        }
    }

}
