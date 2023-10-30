package com.entropia.bookshelf.network

import com.entropia.bookshelf.model.VolumeList
import retrofit2.http.GET
import retrofit2.http.Query

interface BookshelfApiService {

    @GET("volumes")
    suspend fun getVolumes(@Query("q") topic: String): VolumeList
}
