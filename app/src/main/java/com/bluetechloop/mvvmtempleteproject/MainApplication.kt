package com.bluetechloop.mvvmtempleteproject

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.util.Log
import com.akexorcist.localizationactivity.core.LocalizationApplicationDelegate
import com.akexorcist.localizationactivity.ui.LocalizationApplication
import dagger.hilt.android.HiltAndroidApp
import java.util.Locale

@HiltAndroidApp
class MainApplication : LocalizationApplication() {

    //    lateinit var socketManager:SocketManager
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application
            private set
    }

    private val localizationDelegate = LocalizationApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        localizationDelegate.setDefaultLanguage(base, Locale.ENGLISH)
        super.attachBaseContext(localizationDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localizationDelegate.onConfigurationChanged(this)
    }

    override fun getApplicationContext(): Context {
        return localizationDelegate.getApplicationContext(super.getApplicationContext())
    }

    override fun getDefaultLanguage(context: Context): Locale {
        return Locale.getDefault()
    }

    override fun getResources(): Resources {
        return localizationDelegate.getResources(baseContext, super.getResources())
    }

    object LogUtil {
        private const val TAG = "ZoomApp"

        @JvmStatic
        fun d(message: String) {
            Log.d(TAG, "This is from Log debug:--->$message")
        }

        @JvmStatic
        fun v(message: String) {
            Log.d(TAG, "This is from Log verbose:--->$message")
        }

        @JvmStatic
        fun e(message: String) {
            Log.d(TAG, "This is from Log error:--->$message")
        }

        // Add other log levels as needed
    }
}