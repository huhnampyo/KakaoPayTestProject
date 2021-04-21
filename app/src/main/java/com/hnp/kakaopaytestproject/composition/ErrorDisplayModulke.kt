package com.hnp.kakaopaytestproject.composition

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Singleton

@Module
class ErrorDisplayModulke {
    @Provides
    @Singleton
    fun providesErrorSub(): PublishSubject<String> = PublishSubject.create()
}