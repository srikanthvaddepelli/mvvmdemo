package com.example.mvvmdemo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.InboxInput
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.domain.usecase.GetInboxSMSUseCase
import com.example.mvvmdemo.ui.StateValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class SMSListingActivityViewModel(
    private val getInboxSMSUseCase: GetInboxSMSUseCase
) : ViewModel() {

    private val _smsList =
        MutableStateFlow<StateValue<List<SMSModel>>>(StateValue.Idle)
    val smsList : StateFlow<StateValue<List<SMSModel>>> = _smsList

    private var offset = 0

    fun fetchSMS() {
        viewModelScope.launch {
            _smsList.emit(StateValue.Loading)
            val data = getInboxSMSUseCase.execute(InboxInput(offset))
            when (data) {
                is Result.Success -> {
                    data.data?.let {
                        offset += data.data.size
                    }
                    _smsList.emit(StateValue.Success(data.data))
                }
                is Result.Error -> {
                    _smsList.emit(StateValue.Failure(data.throwable))
                }
            }
        }
    }


}