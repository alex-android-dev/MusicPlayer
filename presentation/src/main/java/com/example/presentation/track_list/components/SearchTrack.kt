package com.example.presentation.track_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.theme.MusicPlayerTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTrack(
    searchText: String,
    expanded: Boolean,
    onSearchTextChanged: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearchTextInput: (String) -> Unit,
) {

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText,
                onQueryChange = { text ->
                    onSearchTextChanged(text)
                },
                onSearch = { text ->
                    onSearchTextInput(text)
                },
                expanded = expanded,
                onExpandedChange = { isChange ->
                    onExpandedChange(isChange)
                },
                placeholder = {
                    Text("Введите название трека") // TODO add to res
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null, // todo
                    )
                }
            )
        },
        expanded = false,
        onExpandedChange = {}
    ) {}

}

@Composable
@Preview
fun SearchTrackPreview() {

    MusicPlayerTheme {
        SearchTrack(
            "",
            true,
            onSearchTextChanged = {},
            onExpandedChange = {},
            onSearchTextInput = {},
        )
    }


}