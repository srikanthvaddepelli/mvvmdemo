package com.example.mvvmdemo.domain.model

data class SMSModel(
    val threadId: Int,
    val date: Int,
    val address: String,
    val body: String,
    val type: Int,
    val person: Int,
    val read: Int,
    val hoursAgo: Int,
    )
