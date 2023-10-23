package com.ksnk.android

data class Themes(
    val id: Long,
    val number: Long,
    val title: String,
    val progress: Int = 0,
    val openQuestions: Int = 0,
    val allQuestions: Int = 0
)
