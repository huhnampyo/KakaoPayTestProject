package com.hnp.kakaopaytestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import com.hnp.kakaopaytestproject.presentation.main.BookDetailFragment
import com.hnp.kakaopaytestproject.presentation.main.MainFragment
import com.hnp.kakaopaytestproject.presentation.main.viewmodel.MainViewModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as KakaoApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            supportFragmentManager.commit {
                replace<MainFragment>(R.id.mainFragmentHolder)
                setReorderingAllowed(true)
            }
        }

        viewModel.selectedBook.nonNullObserve(this){
            supportFragmentManager.commit {
                add<BookDetailFragment>(R.id.mainFragmentHolder)
                setReorderingAllowed(true)
                addToBackStack("detail") // name can be null
            }
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStack()
        }else{
            viewModel.clear()
            super.onBackPressed()
        }
    }

    private fun clearBackStack() {
        while (supportFragmentManager.backStackEntryCount > 0){
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return viewModelFactory
    }
}