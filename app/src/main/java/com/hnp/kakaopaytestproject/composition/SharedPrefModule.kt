package com.hnp.kakaopaytestproject.composition

import android.content.Context
import android.content.SharedPreferences
import com.hnp.kakaopaytestproject.KakaoApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPrefModule {
    @Provides
    @Singleton
    fun providesSharedPref(app: KakaoApp): SharedPreferences {
        val packageName = app.packageManager.getPackageInfo(app.packageName, 0)

        return app.getSharedPreferences(packageName.packageName, Context.MODE_PRIVATE)
    }
}