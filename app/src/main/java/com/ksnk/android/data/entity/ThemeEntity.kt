package com.ksnk.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class ThemeEntity(
    @PrimaryKey(autoGenerate = true)
    val themeId: Long = 0,
    val name: String
)
