package com.example.presentation.track_list_screen

import DarkBlue
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.TrackListState
import com.example.domain.interactors.TrackListInteractor
import com.example.presentation.presentation.theme.TrackCard
import com.example.presentation.track_list_screen.components.SearchTrack
import com.example.presentation.track_screen.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListView(
    padding: PaddingValues,
    interactor: TrackListInteractor,
    onClickTrack: (Long) -> Unit,
) {
    val viewModel: TrackListViewModel = viewModel(
        factory = TrackListViewModelFactory(interactor)
    )

    val screenState = viewModel.trackListStatus.collectAsState()
    var isSearching by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (isSearching) {
                TopBar(
                    imgVector = Icons.AutoMirrored.Filled.ArrowBack,
                    text = "Searching results"
                ) {
                    viewModel.loadTrackList()
                    isSearching = false
                }
            }
        }
    ) { paddingValuesSecond ->

        val currentPadding = if (isSearching) paddingValuesSecond else padding

            Column(
                Modifier.padding(currentPadding)
            ) {
                /** Стейт для поиска **/
                val searchText = remember { mutableStateOf("") }
                val expanded = remember { mutableStateOf(false) }

                SearchTrack(
                    text = searchText.value,
                    expanded = expanded.value,
                    onSearchTextChanged = { searchText.value = it },
                    onExpandedChange = { expanded.value = it },
                    onSearchTextInput = {
                        viewModel.loadTrackListByName(searchText.value)
                        isSearching = true
                    },
                )

                Spacer(Modifier.height(20.dp))

                when (val state = screenState.value) {
                    is TrackListState.Initial -> {}

                    is TrackListState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = DarkBlue)
                            Log.d("TrackListView", "Loading")
                        }
                    }

                    is TrackListState.Loaded -> {
                        val lazyStateList = rememberLazyListState()

                        LazyColumn(
                            modifier = Modifier.padding(),
                            state = lazyStateList
                        ) {

                            items(
                                items = state.trackList,
                                key = { it.id },
                            ) {
                                TrackCard(
                                    it,
                                    onClickItem = { trackId ->
                                        onClickTrack(trackId)
                                    }
                                )
                            }

                        }
                    }

                    is TrackListState.Failed -> {

                    }
                }
            }
    }
}