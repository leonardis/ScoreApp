package com.leonardis.scoreapp.viewmodels

import com.leonardis.scoreapp.actions.DataActions
import com.leonardis.scoreapp.base.di.ActivityScope
import com.leonardis.scoreapp.controllers.FixtureController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class FixtureViewModel @Inject constructor(private val controller: FixtureController) {

    private val compositeDisposable = CompositeDisposable()
    private val publishSubject: PublishSubject<DataActions> = PublishSubject.create()

    init {
        compositeDisposable += controller.getFixtures()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handelSuccess, ::handleError)
    }

    fun getCompetitions() {
        compositeDisposable += controller.getFixturesCompetitions()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ publishSubject.onNext(DataActions.OnCompetitionsRetrieved(it)) }, ::handleError)
    }

    fun getFixturesFilteredBy(competitionId: Int? = null) {
        compositeDisposable += controller.getFixturesFilteredBy(competitionId)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handelSuccess, ::handleError)
    }

    private fun handelSuccess(fixtures: List<Any>) {
        publishSubject.onNext(DataActions.OnDataRetrieved(fixtures))
    }

    private fun handleError(error: Throwable) {
        publishSubject.onNext(DataActions.OnError(error.message.orEmpty()))
    }

    fun getFixtureObservable(): Observable<DataActions> = publishSubject.hide()

}