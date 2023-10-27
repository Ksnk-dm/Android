package com.ksnk.android.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ksnk.android.data.dao.LibraryDao
import com.ksnk.android.data.dao.QuestionDao
import com.ksnk.android.data.dao.ThemeDao
import com.ksnk.android.data.entity.LibraryEntity
import com.ksnk.android.data.entity.QuestionEntity
import com.ksnk.android.data.entity.ThemeEntity

@Database(
    entities = [
        QuestionEntity::class,
        ThemeEntity::class,
        LibraryEntity::class],
    version = 1, exportSchema = false
)

abstract class DataBase : RoomDatabase() {
    abstract val themeDao: ThemeDao
    abstract val questionDao: QuestionDao
    abstract val libraryDao: LibraryDao
}