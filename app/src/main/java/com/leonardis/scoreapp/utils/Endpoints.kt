package com.leonardis.scoreapp.utils

import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.models.Results
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface Endpoints {

    @GET("results.json")
    fun getResults(): Single<List<Results>>

    @GET("fixtures.json")
    fun getFixtures(): Single<List<Fixtures>>

}