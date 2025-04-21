package com.example.musicplayer

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.OptIn
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.data.repository.ApiRepositoryImpl
import com.example.data.repository.LocalRepositoryImpl
import com.example.data.repository.local.ContentResolverHelper
import com.example.domain.interactors.PlayTrackInteractor
import com.example.domain.interactors.TrackListInteractor
import com.example.musicplayer.navigation.AppNavGraph
import com.example.musicplayer.navigation.BottomNavigationBar
import com.example.musicplayer.navigation.ScreenRoute
import com.example.musicplayer.navigation.rememberNavigationState
import com.example.presentation.theme.MusicPlayerTheme
import com.example.presentation.track_list_screen.TrackListView
import com.example.presentation.track_screen.PlayTrackScreen

class MainActivity : ComponentActivity() {

    private val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        Manifest.permission.READ_MEDIA_AUDIO
    } else {
        Manifest.permission.READ_EXTERNAL_STORAGE
    }

    @OptIn(UnstableApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            val navState = rememberNavigationState()
            val apiRepositoryImpl = ApiRepositoryImpl()
            val apiTrackListInteractor = TrackListInteractor(apiRepositoryImpl)

            val localTrackListRepositoryImpl = LocalRepositoryImpl(ContentResolverHelper(this))
            val localTrackListInteractor = TrackListInteractor(localTrackListRepositoryImpl)

            val apiPlayTrackInteractor = PlayTrackInteractor(apiRepositoryImpl)
            val localPlayTrackInteractor = PlayTrackInteractor(localTrackListRepositoryImpl)

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
                                interactor = apiTrackListInteractor,
                                onClickTrack = { trackId ->
                                    navState.navigateToMusicPlayer(trackId)
                                },
                            )
                        },
                        trackLocalListContent = {
                            TrackListView(
                                padding = padding,
                                interactor = localTrackListInteractor,
                                onClickTrack = { trackId ->
                                    navState.navigateToMusicPlayer(trackId)
                                }
                            )
                        },
                        playTrackContent = { trackId ->
                            Log.d("MainActivity", "trackId: $trackId")
                            PlayTrackScreen(
                                trackId = trackId,
                                onBackPressed = {
                                    navState.navHostController.popBackStack()
                                },
                                interactor = apiPlayTrackInteractor,
                            )
                        },
                    )
                }
            }


        }
    }
}



