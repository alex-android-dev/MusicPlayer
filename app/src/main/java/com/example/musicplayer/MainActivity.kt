package com.example.musicplayer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.repository.TrackRepositoryImpl
import com.example.domain.interactors.TrackListInteractor
import com.example.musicplayer.navigation.BottomNavigationBar
import com.example.musicplayer.navigation.rememberNavigationState
import com.example.presentation.theme.MusicPlayerTheme
import com.example.presentation.track_list.TrackListView
import com.example.presentation.track_list.TrackListViewModel
import com.example.presentation.track_list.TrackListViewModelFactory

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navState = rememberNavigationState()
            val repositoryImpl = TrackRepositoryImpl()
            val trackListInteractor = TrackListInteractor(repositoryImpl)

            val viewModel: TrackListViewModel = viewModel(
                factory = TrackListViewModelFactory(trackListInteractor)
            )

            MusicPlayerTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navState) },
                ) { padding ->
                    padding
                    AppNavGraph(
                        navHostController = navState.navHostController,
                        trackListApiContent = { TrackListView(viewModel) },
                        trackListDownloadedContent = { },
                    )
                }
            }


        }
    }
}