package com.example.presentation.track_list

import DarkBlue
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.TrackListState
import com.example.domain.interactors.TrackListInteractor
import com.example.presentation.presentation.theme.TrackCard
import com.example.presentation.track_list.components.SearchTrack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListView(
    paddingValues: PaddingValues,
    trackListInteractor: TrackListInteractor,
) {
    val viewModel: TrackListViewModel = viewModel(
        factory = TrackListViewModelFactory(trackListInteractor)
    )

    val screenState = viewModel.trackListStatus.collectAsState()

    Column(
        Modifier.padding(paddingValues)
    ) {
        /** Стейт для поиска **/
        val searchText = remember { mutableStateOf("") }
        val expanded = remember { mutableStateOf(false) }

        SearchTrack(
            searchText = searchText.value,
            expanded = expanded.value,
            onSearchTextChanged = { },
            onExpandedChange = { }
        ) { }

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
                        TrackCard(it)
                    }

                }
            }

            is TrackListState.Failed -> {

            }
        }
    }
}