package com.hnp.kakaopaytestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.hnp.kakaopaytestproject.presentation.main.MainFragment
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KakaoApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.mainFragmentHolder,
                    MainFragment.newInstance()
                )
                .commit()
        }
    }
}