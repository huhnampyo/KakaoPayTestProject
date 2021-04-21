package com.hnp.kakaopaytestproject.composition

import com.hnp.kakaopaytestproject.KakaoApp
import com.hnp.kakaopaytestproject.MainActivity
import com.hnp.kakaopaytestproject.composition.presentation.BookDetailComponent
import com.hnp.kakaopaytestproject.composition.presentation.MainComponent
import com.hnp.kakaopaytestproject.composition.presentation.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ServiceModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun mainComponent(): MainComponent.Factory

    fun bookDetailComponent(): BookDetailComponent.Factory

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindsApp(app: KakaoApp): Builder

        fun build(): AppComponent
    }
}