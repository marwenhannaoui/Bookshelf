package com.entropia.bookshelf.data

import com.entropia.bookshelf.model.VolumeList
import com.entropia.bookshelf.network.BookshelfApiService

interface VolumesRepository {
    suspend fun getVolumes(topic: String): VolumeList
}

class NetworkVolumesRepository(private val bookshelfApiService: BookshelfApiService) :
    VolumesRepository {
    override suspend fun getVolumes(topic: String): VolumeList =
        bookshelfApiService.getVolumes(topic)

}
