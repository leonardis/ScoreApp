package com.leonardis.scoreapp.repositories

import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.models.Results
import com.leonardis.scoreapp.utils.Endpoints
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ScoreRepository @Inject constructor(private val endpoints: Endpoints) {

    fun getFixtures(): Single<List<Fixtures>> {
        return endpoints.getFixtures()
            .subscribeOn(Schedulers.io())
    }

    fun getResults(): Single<List<Results>> {
        return endpoints.getResults()
            .subscribeOn(Schedulers.io())
    }
}