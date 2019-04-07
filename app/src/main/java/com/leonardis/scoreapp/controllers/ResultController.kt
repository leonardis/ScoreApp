package com.leonardis.scoreapp.controllers

import com.leonardis.scoreapp.models.Competition
import com.leonardis.scoreapp.models.Results
import com.leonardis.scoreapp.repositories.ScoreRepository
import com.leonardis.scoreapp.utils.ONLY_MONTH_FORMAT
import com.leonardis.scoreapp.utils.getDateWithFormat
import io.reactivex.Single
import javax.inject.Inject

class ResultController @Inject constructor(private val repository: ScoreRepository) {

    private lateinit var resultList: List<Results>

    fun getResults(): Single<List<Any>> {
        return repository.getResults()
            .map { results ->
                resultList = results
                results
                    .applySortByMonth()
                    .toSet()
                    .toList()
            }
    }

    fun getResultsCompetitions(): Single<List<Competition>> {
        return Single.just(
            resultList
                .map {
                    it.competitionStage.competition
                }
                .toSet()
                .toList()
        )
    }

    fun getResultsFilteredBy(competitionId: Int? = null): Single<List<Any>> {
        return Single.just(
            competitionId?.let {
                resultList
                    .filter {
                        it.competitionStage.competition.id == competitionId
                    }
                    .applySortByMonth()
                    .toSet()
                    .toList()
            }?: run {
                resultList
                    .applySortByMonth()
                    .toSet()
                    .toList()
            })
    }

    private fun List<Results>.applySortByMonth(): List<Any> {
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