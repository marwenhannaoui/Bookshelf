package com.entropia.bookshelf.fake

import com.entropia.bookshelf.rules.TestDispatcherRule
import com.entropia.bookshelf.ui.screens.BookshelfUiState
import com.entropia.bookshelf.ui.screens.BookshelfViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class BookshelfViewModelTest {
    @get:Rule
    val testDispatcherRule = TestDispatcherRule()

    @Test
    fun bookshelfViewModel_getVolumes_verifyBookshelfUiStateSuccess() = runTest {
        val bookshelfViewModel = BookshelfViewModel(
            volumesRepository = FakeNetworkVolumesRepository()
        )

        assertEquals(
            BookshelfUiState.Success(
                bookshelfViewModel.getBooks(FakeDataSource.volumeList.items)
            ), bookshelfViewModel.bookshelfUiState
        )
    }
}