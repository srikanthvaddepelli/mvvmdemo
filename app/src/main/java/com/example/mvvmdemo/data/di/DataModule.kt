package com.example.mvvmdemo.data.di

import com.example.mvvmdemo.data.repository.SMSRepository
import com.example.mvvmdemo.data.repository.SMSRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single <SMSRepository> {
        SMSRepositoryImpl(
            androidContext()
        )
    }

}
