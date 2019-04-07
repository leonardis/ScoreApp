package com.leonardis.scoreapp.base

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leonardis.scoreapp.ScoreApplication
import com.leonardis.scoreapp.base.di.component.AppComponent
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MessageEvent(val id: Int)

abstract class BaseBottomSheetDialogFragment: BottomSheetDialogFragment() {

    protected abstract fun onFragmentInject()
    val compositeDisposable = CompositeDisposable()

    fun getAppcomponent(): AppComponent = ScoreApplication.appComponent

    fun showError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun defaultSubscribe(event: MessageEvent){}

}
