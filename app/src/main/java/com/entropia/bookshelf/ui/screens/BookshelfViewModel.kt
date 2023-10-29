package com.entropia.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.entropia.bookshelf.BookshelfApplication
import com.entropia.bookshelf.data.VolumesRepository
import com.entropia.bookshelf.model.Volume
import kotlinx.coroutines.launch


sealed interface BookshelfUiState {
    data class Success(val volumes: List<Volume>) : BookshelfUiState
    object Error : BookshelfUiState

    object Loading : BookshelfUiState
}


class BookshelfViewModel(private val volumesRepository: VolumesRepository) : ViewModel() {
    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getVolumes()
    }

    fun getVolumes() {
        viewModelScope.launch {

            bookshelfUiState = try {
                BookshelfUiState.Success(volumesRepository.getVolumes())
            } catch (e: Exception){
                BookshelfUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BookshelfApplication)
                val volumesRepository = application.appContainer.volumesRepository
                BookshelfViewModel(volumesRepository)
            }
        }
    }


}