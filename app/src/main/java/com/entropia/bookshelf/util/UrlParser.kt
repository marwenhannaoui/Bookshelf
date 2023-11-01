package com.entropia.bookshelf.util

fun parseUrl(httpUrl: String?): String? =
    httpUrl?.replace("http://", "https://")

fun convertStringToQuery(text: String?): String? {
    val words = text?.split(" ")
    return if (words != null) {
        if (words.size == 1) {
            words[0]
        } else {
            words.joinToString("+")
        }
    } else
        null
}
