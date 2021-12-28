package com.example.mvvmdemo.domain.repository

import android.content.Context
import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.SMSModel

interface SMSRepository {
   suspend fun getInboxSMS(context: Context, offset: Int): Result<List<SMSModel>>
}