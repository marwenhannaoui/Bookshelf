package com.entropia.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Volume(val selfLink: String)


@Serializable
data class Book(
    val title: String,
    val authors: Array<String>,
    @SerialName(value = "volumeInfo.imageLinks.thumbnail")
    val imgSrc: String
)


