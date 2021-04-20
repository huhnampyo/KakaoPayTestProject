package com.hnp.kakaopaytestproject.composition.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import com.hnp.kakaopaytestproject.presentation.viewmodel.ViewModelInjectedFactory
import com.kbstar.land.composition.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelInjectedFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel
}