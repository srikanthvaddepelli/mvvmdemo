package com.example.mvvmdemo.domain.usecase

import com.example.mvvmdemo.domain.Result
import com.example.mvvmdemo.domain.model.InboxInput
import com.example.mvvmdemo.domain.model.SMSModel
import com.example.mvvmdemo.domain.usecase.baseusecase.BaseUseCase
import kotlinx.coroutines.flow.Flow

interface GetInboxSMSUseCase: BaseUseCase<InboxInput,Result<List<SMSModel>>>