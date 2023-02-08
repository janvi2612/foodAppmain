package com.example.foodapp.Util

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import com.example.foodapp.Util.PrefManager

import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PrefManager.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}