package com.example.presentation.presentation.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.presentation.TrackListScreen.TrackListScreenState

class MainActivity : ComponentActivity() {

    @SuppressLint("PermissionLaunchedDuringComposition")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            /*
            val navState = rememberNavigationState()
            val viewModel: TrackListViewModel = viewModel()
            viewModel.screenState

            val notificationPermission = rememberPermissionState(
                permission = Manifest.permission.POST_NOTIFICATIONS
            )
            val context = LocalContext.current

            MusicPlayerTheme {

                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navState) },
                ) { padding ->
                    if (!notificationPermission.status.isGranted) notificationPermission.launchPermissionRequest()

                    padding

                    PlayerScreenTest(context)

//                    AppNavGraph(
//                        navHostController = navState.navHostController,
//                        trackListApiContent = { TrackListView(padding) },
//                        trackListDownloadedContent = { Test() },
//                    ) // TODO наш AppNavGraph
                }
            }

             */
        }
    }
}

/*
@Composable
fun PlayerScreenTest(context: Context) {

    val viewModel: TrackListViewModel = viewModel()
    val screenState = viewModel.screenState.collectAsState(TrackListScreenState.Initial)
    AppMusicPlayer.initPLayer(context)

    when (val state = screenState.value) {
        is TrackListScreenState.Tracks -> {

            LaunchedEffect(true) {
                val serviceIntent = Intent(context, PlaybackService::class.java)
                context.startService(serviceIntent)
            }


            Log.d("PlayerScreenTest", "state is Tracks")
//            AppMusicPlayer.playTrackList(state.trackList)

            AndroidView(factory = {
                PlayerView(context).apply {
//                    this.player = AppMusicPlayer.getPlayer()
                }
            })

            DisposableEffect(Unit) {
                onDispose {
//                    AppMusicPlayer.releasePlayer()
                }
            }
        }

        else -> {}
    }

}

//private fun askPermission() {
//    if (ContextCompat.checkSelfPermission(
//            this,
//            Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//        ) != Manifest.permission.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION
//    )
//}
*/