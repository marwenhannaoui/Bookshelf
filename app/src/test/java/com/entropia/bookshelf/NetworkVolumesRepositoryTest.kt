package com.entropia.bookshelf

import com.entropia.bookshelf.data.NetworkVolumesRepository
import com.entropia.bookshelf.fake.FakeBookshelfApiService
import com.entropia.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkVolumesRepositoryTest {
    @Test
    fun networkVolumesRepository_getVolumes_verifyVolumeList() = runTest {
        val repository=NetworkVolumesRepository(
            bookshelfApiService = FakeBookshelfApiService()
        )
        assertEquals(FakeDataSource.volumeList, repository.getVolumes(""))
    }
}