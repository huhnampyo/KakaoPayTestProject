package com.hnp.kakaopaytestproject.composition.presentation

import com.hnp.kakaopaytestproject.presentation.main.BookDetailFragment
import dagger.Subcomponent

@Subcomponent
interface BookDetailComponent {
    fun inject(fragment: BookDetailFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): BookDetailComponent
    }
}