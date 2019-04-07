package com.leonardis.scoreapp.viewmodels

import com.leonardis.scoreapp.actions.DataActions
import com.leonardis.scoreapp.controllers.ResultController
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class ResultViewModel @Inject constructor(private val controller: ResultController) {

    private val compositeDisposable = CompositeDisposable()
    private val publishSubject: PublishSubject<DataActions> = PublishSubject.create()

    init {
        compositeDisposable += controller.getResults()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handelSuccess, ::handleError)
    }

    fun getCompetitions() {
        compositeDisposable += controller.getResultsCompetitions()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ publishSubject.onNext(DataActions.OnCompetitionsRetrieved(it)) }, ::handleError)
    }

    fun getResultsFilteredBy(competitionId: Int? = null) {
        compositeDisposable += controller.getResultsFilteredBy(competitionId)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::handelSuccess, ::handleError)
    }

    private fun handelSuccess(results: List<Any>) {
        publishSubject.onNext(DataActions.OnDataRetrieved(results))
    }

    private fun handleError(error: Throwable) {
        publishSubject.onNext(DataActions.OnError(error.message.orEmpty()))
    }

    fun getFixtureObservable(): Observable<DataActions> = publishSubject.hide()

}