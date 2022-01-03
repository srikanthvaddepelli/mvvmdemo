package com.example.mvvmdemo.ui

import android.app.Application
import com.example.mvvmdemo.data.di.dataModule
import com.example.mvvmdemo.domain.di.useCaseModule
import com.example.mvvmdemo.ui.di.uiModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            //inject Android context
            androidContext(this@MyApplication)

            modules(listOf(
                uiModule,
                useCaseModule,
                dataModule
            ))
        }
    }
}