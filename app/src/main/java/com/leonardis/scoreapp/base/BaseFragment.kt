package com.leonardis.scoreapp.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.leonardis.scoreapp.ScoreApplication
import com.leonardis.scoreapp.base.di.component.AppComponent
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment: Fragment() {

    protected abstract fun onFragmentInject()
    val compositeDisposable = CompositeDisposable()

    fun getAppcomponent(): AppComponent = ScoreApplication.appComponent

    fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}
