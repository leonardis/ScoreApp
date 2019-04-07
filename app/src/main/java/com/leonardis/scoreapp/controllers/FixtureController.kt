package com.leonardis.scoreapp.controllers

import com.leonardis.scoreapp.base.di.ActivityScope
import com.leonardis.scoreapp.models.Competition
import com.leonardis.scoreapp.models.Fixtures
import com.leonardis.scoreapp.repositories.ScoreRepository
import com.leonardis.scoreapp.utils.ONLY_MONTH_FORMAT
import com.leonardis.scoreapp.utils.getDateWithFormat
import io.reactivex.Single
import javax.inject.Inject

class FixtureController @Inject constructor(private val repository: ScoreRepository) {

    private lateinit var fixtureList: List<Fixtures>

    fun getFixtures(): Single<List<Any>> {
        return repository.getFixtures()
            .map { fixtures ->
                fixtureList = fixtures
                fixtures
                    .applySortByMonth()
                    .toSet()
                    .toList()
            }
    }

    fun getFixturesCompetitions(): Single<List<Competition>> {
        return Single.just(
            fixtureList
                .map {
                    it.competitionStage.competition
                }
                .toSet()
                .toList()
        )
    }

    fun getFixturesFilteredBy(competitionId: Int? = null): Single<List<Any>> {
        return Single.just(
            competitionId?.let {
                fixtureList
                    .filter {
                        it.competitionStage.competition.id == competitionId
                    }
                    .applySortByMonth()
                    .toSet()
                    .toList()
            }?: run {
                fixtureList
                    .applySortByMonth()
                    .toSet()
                    .toList()
            })
    }

    private fun List<Fixtures>.applySortByMonth(): List<Any> {
        val fixtures = this
        return mutableListOf<Any>().apply {
                fixtures.sortedBy { getDateWithFormat(it.date, ONLY_MONTH_FORMAT) }
                    .map {
                        this.add(getDateWithFormat((it).date, ONLY_MONTH_FORMAT))
                        this.add(it)
                    }
        }
    }

}