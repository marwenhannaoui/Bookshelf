package com.entropia.bookshelf

import android.app.Application
import com.entropia.bookshelf.data.AppContainer
import com.entropia.bookshelf.data.DefaultAppContainer

class BookshelfApplication : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}