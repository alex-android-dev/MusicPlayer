package com.example.musicplayer

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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.data.repository.TrackRepositoryImpl
import com.example.domain.interactors.TrackListInteractor
import com.example.musicplayer.navigation.AppNavGraph
import com.example.musicplayer.navigation.BottomNavigationBar
import com.example.musicplayer.navigation.ScreenRoute
import com.example.musicplayer.navigation.rememberNavigationState
import com.example.presentation.theme.MusicPlayerTheme
import com.example.presentation.track_list_screen.TrackListView
import com.example.presentation.track_screen.TrackScreen

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val trackId = remember { mutableLongStateOf(-1) } // TODO заглушка
            val navState = rememberNavigationState()
            val repositoryImpl = TrackRepositoryImpl()
            val trackListInteractor = TrackListInteractor(repositoryImpl)

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
                ) { paddingValues ->

                    AppNavGraph(
                        navHostController = navState.navHostController,
                        trackApiListContent = {
                            TrackListView(
                                paddingValues,
                                trackListInteractor,
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
                            TrackScreen(
                                trackId = trackId,
                                onBackPressed = {
                                    navState.navHostController.popBackStack()
                                }
                            )
                        },
                    )
                }
            }


        }
    }
}