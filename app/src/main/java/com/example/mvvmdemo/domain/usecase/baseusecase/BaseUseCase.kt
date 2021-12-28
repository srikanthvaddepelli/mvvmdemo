package com.example.mvvmdemo.domain.usecase.baseusecase

interface BaseUseCase<in InputT,out OutputT> {
    suspend fun execute(inputT: InputT): OutputT
}