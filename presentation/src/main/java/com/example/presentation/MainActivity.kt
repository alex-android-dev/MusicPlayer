package com.example.presentation

import android.annotation.SuppressLint
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
import com.example.presentation.navigation.AppNavGraph
import com.example.presentation.navigation.BottomNavigationBar
import com.example.presentation.navigation.rememberNavigationState
import com.example.presentation.theme.MusicPlayerTheme
import com.example.presentation.track_list.TrackListView
import com.example.presentation.track_list.TrackListViewModel

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navState = rememberNavigationState()
            val viewModel: TrackListViewModel = viewModel()

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