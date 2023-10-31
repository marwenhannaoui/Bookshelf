package com.entropia.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VolumeList(
    val items:List<Volume>
)

@Serializable
data class Volume(@SerialName(value="volumeInfo")val book: Book)


@Serializable
data class Book(
    val title: String,
    val authors: List<String>,
    val imageLinks: Map<String,String> = emptyMap()
)


