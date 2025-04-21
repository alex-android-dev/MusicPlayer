package com.example.musicplayer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.data.repository.TrackRepositoryImpl
import com.example.domain.interactors.PlayTrackInteractor
import com.example.domain.interactors.TrackListInteractor
import com.example.musicplayer.navigation.AppNavGraph
import com.example.musicplayer.navigation.BottomNavigationBar
import com.example.musicplayer.navigation.ScreenRoute
import com.example.musicplayer.navigation.rememberNavigationState
import com.example.presentation.theme.MusicPlayerTheme
import com.example.presentation.track_list_screen.TrackListView
import com.example.presentation.track_screen.PlayTrackScreen
import com.example.presentation.track_screen.PlayTrackViewModelFactory

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navState = rememberNavigationState()
            val repositoryImpl = TrackRepositoryImpl()
            val trackListInteractor = TrackListInteractor(repositoryImpl)
            val playTrackInteractor = PlayTrackInteractor(repositoryImpl)

            MusicPlayerTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    bottomBar = {
                        val currentRoute = navState.navHostController.currentBackStackEntryAsState()
                            .value?.destination?.route

                        if (currentRoute !=
                            ScreenRoute.PlayTrack.route
                        ) {
                            BottomNavigationBar(navState)
                        }
                    },
                ) { padding ->
                    AppNavGraph(
                        navHostController = navState.navHostController,
                        trackApiListContent = {
                            TrackListView(
                                padding = padding,
                                interactor = trackListInteractor,
                                onClickTrack = { trackId ->
                                    navState.navigateToMusicPlayer(trackId)
                                },
                            )
                        },
                        trackLocalListContent = {
                            Text("trackLocalListContent") // TODO Заглушка
                        },
                        playTrackContent = { trackId ->
                            Log.d("MainActivity", "trackId: $trackId")
                            PlayTrackScreen(
                                trackId = trackId,
                                onBackPressed = {
                                    navState.navHostController.popBackStack()
                                },
                                interactor = playTrackInteractor,
                            )
                        },
                    )
                }
            }


        }
    }
}