package com.entropia.bookshelf.fake

import com.entropia.bookshelf.data.VolumesRepository
import com.entropia.bookshelf.model.VolumeList

class FakeNetworkVolumesRepository:VolumesRepository{
    override suspend fun getVolumes(topic: String): VolumeList {
        return FakeDataSource.volumeList
    }
}