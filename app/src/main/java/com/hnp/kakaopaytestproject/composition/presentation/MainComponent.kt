package com.hnp.kakaopaytestproject.composition.presentation

import com.hnp.kakaopaytestproject.presentation.main.MainFragment
import dagger.Subcomponent

@Subcomponent
interface MainComponent {
    fun inject(fragment: MainFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
}