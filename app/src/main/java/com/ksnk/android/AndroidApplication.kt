package com.ksnk.android

import android.app.Application
import com.ksnk.android.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(
                viewModelModule,
                repositoryModule,
                databaseModule,
                sharedPrefsModule
            )
        }
    }
}