package com.example.musicplayer

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.data.repository.TrackRepositoryImpl
import com.example.domain.TrackRepository
import com.example.domain.interactors.TrackListInteractor
import com.example.presentation.track_list.TrackListViewModel
import com.example.presentation.track_list.TrackListViewModelFactory

class MusicPlayerApp : Application() {

    private lateinit var viewModel: TrackListViewModel

    override fun onCreate() {
        super.onCreate()
    }
}