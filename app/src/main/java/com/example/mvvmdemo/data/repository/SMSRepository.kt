package com.example.mvvmdemo.data.repository

import android.content.Context
import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.SMSModel

interface SMSRepository {
   suspend fun getInboxSMS(offset: Int): Result<List<SMSModel>>
}