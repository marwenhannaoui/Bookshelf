package com.entropia.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entropia.bookshelf.model.Volume
import kotlinx.coroutines.launch


sealed interface BookshelfUiState {
    data class Success(val test: String) : BookshelfUiState
    object Error : BookshelfUiState

    object Loading : BookshelfUiState
}


class BookshelfViewModel : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set


    fun getVolumes() {
        viewModelScope.launch {

            val listResult = emptyList<Volume>() //TODO
            bookshelfUiState = BookshelfUiState.Success(
                "Success: ${listResult.size} Mars photos retrieved"
            )

        }
    }


}