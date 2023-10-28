package com.entropia.bookshelf.network

import com.entropia.bookshelf.model.Volume
import retrofit2.http.GET

interface BookshelfApiService {
    @GET
    suspend fun getVolumes():List<Volume>
}
