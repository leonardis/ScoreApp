package com.leonardis.scoreapp.actions

import com.leonardis.scoreapp.models.Competition

sealed class DataActions {
    class OnDataRetrieved(val data: List<Any>) : DataActions()
    class OnError(val error: String) : DataActions()
    class OnCompetitionsRetrieved(val competitions: List<Competition>) : DataActions()
}
