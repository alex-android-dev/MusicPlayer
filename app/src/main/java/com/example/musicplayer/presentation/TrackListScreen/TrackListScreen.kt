package com.example.musicplayer.presentation.TrackListScreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musicplayer.presentation.theme.DarkBlue
import com.example.musicplayer.presentation.theme.TrackCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListView(
    padding: PaddingValues,
) {
    val viewModel: TrackListViewModel = viewModel()
    val screenState = viewModel.screenState.collectAsState(TrackListScreenState.Initial)

    Column {
        val searchText =
            remember {
                mutableStateOf("")
            }
        val expanded =
            remember { mutableStateOf(false) }

        SearchBar(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchText.value,
                    onQueryChange = { text ->
                        // Обновление текста при изменении
                        searchText.value = text
                    },
                    onSearch = {

                    },
                    expanded = expanded.value,
                    onExpandedChange = {
                        expanded.value = it
                    },
                    placeholder = {
                        Text("Input your text") // TODO add to res
                    }
                )
            },
            expanded = false, // Отвечает за то, чтобы раскрыть на весь экран
            onExpandedChange = {}
        ) {
            // TODO доделать реализацию поиска
        }

        Spacer(Modifier.height(10.dp))

        when (val state = screenState.value) {
            is TrackListScreenState.Initial -> {}

            is TrackListScreenState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = DarkBlue)
                    Log.d("TrackListView", "Loading")
                }
            }

            is TrackListScreenState.Tracks -> {

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
        }
    }
}