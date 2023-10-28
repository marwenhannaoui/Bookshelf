package com.entropia.bookshelf.data

import com.entropia.bookshelf.model.Volume
import com.entropia.bookshelf.network.BookshelfApiService

interface VolumesRepository {
    suspend fun getVolumes(): List<Volume>
}

class NetworkVolumesRepository(private val bookshelfApiService: BookshelfApiService) :
    VolumesRepository {
    override suspend fun getVolumes(): List<Volume> = bookshelfApiService.getVolumes()
    }
