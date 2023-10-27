package com.ksnk.android.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.ksnk.android.data.DataBase
import com.ksnk.android.data.dao.QuestionDao
import com.ksnk.android.data.dao.ThemeDao
import com.ksnk.android.data.repository.RepositoryQuestion
import com.ksnk.android.data.repository.RepositoryQuestionImpl
import com.ksnk.android.data.repository.RepositoryTheme
import com.ksnk.android.data.repository.RepositoryThemeImpl
import com.ksnk.android.ui.question.QuestionViewModel
import com.ksnk.android.ui.setting.SettingViewModel
import com.ksnk.android.ui.splash.SplashViewModel
import com.ksnk.android.ui.themes.ThemesViewModel
import com.ksnk.android.ui.themesQuestions.ThemesQuestionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): DataBase {
        return Room.databaseBuilder(application, DataBase::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideQuestionDao(database: DataBase): QuestionDao {
        return database.questionDao
    }

    fun provideThemeDao(database: DataBase): ThemeDao {
        return database.themeDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideQuestionDao(get()) }
    single { provideThemeDao(get()) }
}

val sharedPrefsModule = module {
    single<SharedPreferences> { get<Context>().getSharedPreferences("my_prefs", Context.MODE_PRIVATE) }
}

val repositoryModule = module {

    fun provideQuestionRepository(dao: QuestionDao): RepositoryQuestion {
        return RepositoryQuestionImpl(dao)
    }

    fun provideThemeRepository(dao: ThemeDao): RepositoryTheme {
        return RepositoryThemeImpl(dao)
    }
    single { provideQuestionRepository(get()) }
    single { provideThemeRepository(get()) }
}

val viewModelModule = module {
    viewModel { SplashViewModel(get(), get(), get()) }
    viewModel { ThemesViewModel(get(), get()) }
    viewModel { QuestionViewModel(get(), get()) }
    viewModel { ThemesQuestionViewModel(get(), get()) }
    viewModel { SettingViewModel(get(), get(), get()) }
}