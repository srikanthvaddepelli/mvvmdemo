package com.example.mvvmdemo.domain.usecase

import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.InboxInput
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.data.repository.SMSRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetInboxSMSUseCaseImpl(private val smsRepository: SMSRepository): GetInboxSMSUseCase {
    override suspend fun execute(inputT: InboxInput): Result<List<SMSModel>> =
        smsRepository.getInboxSMS(inputT.offset)
    }