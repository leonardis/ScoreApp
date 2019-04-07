package com.leonardis.scoreapp.models

import com.google.gson.annotations.SerializedName

data class CompetitionStage(
    @SerializedName("competition") val competition: Competition
)