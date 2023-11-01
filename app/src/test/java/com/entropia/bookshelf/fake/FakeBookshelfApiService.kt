package com.entropia.bookshelf.fake

import com.entropia.bookshelf.model.VolumeList
import com.entropia.bookshelf.network.BookshelfApiService

class FakeBookshelfApiService : BookshelfApiService{
    override suspend fun getVolumes(topic: String): VolumeList {
        return FakeDataSource.volumeList
    }
}