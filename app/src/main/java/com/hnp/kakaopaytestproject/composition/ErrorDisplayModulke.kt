package com.hnp.kakaopaytestproject.composition

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Singleton

@Module
class ErrorDisplayModule {
    @Provides
    @Singleton
    fun providesErrorSub(): PublishSubject<String> = PublishSubject.create()
}