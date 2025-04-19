package com.example.musicplayer

import android.app.Application
import com.example.presentation.track_list_screen.TrackListViewModel

class MusicPlayerApp : Application() {

    private lateinit var viewModel: TrackListViewModel

    override fun onCreate() {
        super.onCreate()
    }
}