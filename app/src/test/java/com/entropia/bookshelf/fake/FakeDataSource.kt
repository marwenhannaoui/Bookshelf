package com.entropia.bookshelf.fake

import com.entropia.bookshelf.model.Book
import com.entropia.bookshelf.model.Volume
import com.entropia.bookshelf.model.VolumeList

object FakeDataSource {
    private val volume1: Volume = Volume(
        Book(
            title = "title",
            authors = listOf("author1", "author2")
        )
    )
    private val volume2: Volume = Volume(
        Book(
            title = "title",
            authors = listOf("author1", "author2"),
            imageLinks = mapOf("thumbnail" to "url.1")
        )
    )

    private val volumes = listOf(volume1, volume2)

    val volumeList = VolumeList(items = volumes)
}