package com.example.musicplayer.presentation.main

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.musicplayer.domain.Entities.Track
import com.example.musicplayer.navigation.BottomNavigationBar
import com.example.musicplayer.navigation.rememberNavigationState
import com.example.musicplayer.presentation.TrackListScreen.TrackListScreenState
import com.example.musicplayer.presentation.TrackListScreen.TrackListViewModel
import com.example.musicplayer.presentation.theme.MusicPlayerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val navState = rememberNavigationState()

            val viewModel: TrackListViewModel = viewModel()
            viewModel.screenState

            MusicPlayerTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navState) },
                ) { padding ->

                    padding

                    PlayerScreen(applicationContext)

//                    AppNavGraph(
//                        navHostController = navState.navHostController,
//                        trackListApiContent = { TrackListView(padding) },
//                        trackListDownloadedContent = { Test() },
//                    )
                }
            }
        }
    }
}


@Composable
fun PlayerScreen(context: Context) {

    val viewModel: TrackListViewModel = viewModel()
    val screenState = viewModel.screenState.collectAsState(TrackListScreenState.Initial)

    when (val value = screenState.value) {
        is TrackListScreenState.Tracks -> {
            val player = remember {
                ExoPlayer.Builder(context).build().apply {

                    val trackList = value.trackList

                    trackList.forEachIndexed { index, track ->
                        val mediaItem = MediaItem.fromUri(track.compositionUrl)
                        if (index == 0) {
                            setMediaItem(mediaItem)
                        } else {
                            addMediaItem(mediaItem)
                        }
                    }

                    prepare()
                    playWhenReady = true
                }
            }

            AndroidView(factory = {
                PlayerView(
                    context
                ).apply {
                    this.player = player
                }
            })

            DisposableEffect(Unit) {
                onDispose {
                    player.release()
                }
            }
        }

        else -> {}
    }


}