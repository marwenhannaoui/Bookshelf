package com.entropia.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.viewmodel.compose.viewModel
import com.entropia.bookshelf.R
import com.entropia.bookshelf.ui.screens.BookshelfViewModel
import com.entropia.bookshelf.ui.screens.HomeScreen
import com.entropia.bookshelf.util.convertStringToQuery


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookshelfApp(modifier: Modifier = Modifier) {
    val bookshelfViewModel: BookshelfViewModel =
        viewModel(factory = BookshelfViewModel.Factory)
    Scaffold(topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        bottomBar = { SearchAppBar(getBooks = bookshelfViewModel::getVolumes) }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            HomeScreen(
                retryAction = bookshelfViewModel::getVolumes,
                bookshelfUiState = bookshelfViewModel.bookshelfUiState,
                modifier = modifier.fillMaxSize()
            )
        }
    }
}


@Composable
fun SearchAppBar(getBooks: (String) -> Unit) {
    var topic: String by remember { mutableStateOf("") }
    BottomAppBar {
        TextField(
            value = topic,
            placeholder = { Text(text = stringResource(id = R.string.enter_topic)) },
            onValueChange = { newTopic -> topic = newTopic },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "searchIcon"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        Button(onClick = { convertStringToQuery(topic)?.let { getBooks(it) } }) {
            Text(text = stringResource(id = R.string.search))
        }
    }
}