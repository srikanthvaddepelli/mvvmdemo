package com.example.mvvmdemo.domain.model

class GroupModel(
    val hoursAgo: Int,
    val list: MutableList<SMSModel> = mutableListOf()
) {
}