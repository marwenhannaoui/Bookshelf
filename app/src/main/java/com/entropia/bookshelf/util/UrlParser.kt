package com.entropia.bookshelf.util

fun parseUrl(httpUrl: String?): String? =
    httpUrl?.replace("http://", "https://")
