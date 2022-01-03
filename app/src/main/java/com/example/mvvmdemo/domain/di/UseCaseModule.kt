package com.example.mvvmdemo.domain.di

import com.example.mvvmdemo.domain.usecase.GetInboxSMSUseCase
import com.example.mvvmdemo.domain.usecase.GetInboxSMSUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {

    factory<GetInboxSMSUseCase> {
        GetInboxSMSUseCaseImpl(
            smsRepository = get(),
        )
    }

}
