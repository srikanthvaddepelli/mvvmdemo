package com.example.mvvmdemo.domain.usecase

import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.InboxInput
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.domain.repository.SMSRepository

class GetInboxSMSUseCaseImpl(private val smsRepository: SMSRepository): GetInboxSMSUseCase {
    override suspend fun execute(input: InboxInput): Result<List<SMSModel>> = smsRepository.getInboxSMS()
}