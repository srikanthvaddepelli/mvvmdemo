package com.example.mvvmdemo.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.domain.model.SMSModel


class SMSListingActivityViewModel: ViewModel() {

    val smsList: LiveData<List<SMSModel>>
        get() = _smsList
    private val _smsList = MutableLiveData<List<SMSModel>>()

    fun fetchSMS(fromStart: Boolean){

    }


}