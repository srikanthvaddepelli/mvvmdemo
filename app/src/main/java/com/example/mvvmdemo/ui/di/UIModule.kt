package com.example.mvvmdemo.ui.di

import com.example.mvvmdemo.ui.viewmodels.SMSListingActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel {
        SMSListingActivityViewModel(getInboxSMSUseCase = get())
    }

}
