package com.hnp.kakaopaytestproject

import android.app.Application
import com.hnp.kakaopaytestproject.composition.AppComponent
import com.hnp.kakaopaytestproject.composition.DaggerAppComponent
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class KakaoApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler {
            it.printStackTrace()
        }

        appComponent = DaggerAppComponent.builder().bindsApp(this).build()

    }
}