package com.example.presentation.track_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.MusicPlayerTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTrack(
    text: String,
    expanded: Boolean,
    onSearchTextChanged: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit,
    onSearchTextInput: () -> Unit,
) {

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                query = text,
                onQueryChange = { text ->
                    onSearchTextChanged(text)
                },
                onSearch = {
                    onSearchTextInput()
                    onExpandedChange(false)
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
        onExpandedChange = { isChange ->
            onExpandedChange(isChange)
        }
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