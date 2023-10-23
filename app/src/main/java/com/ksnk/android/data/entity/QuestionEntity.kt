package com.ksnk.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val themeId: Long = 0,
    val question: String,
    val answer: String,
    var isOpen: Boolean = false,
    val isComplete: Boolean = false
)
