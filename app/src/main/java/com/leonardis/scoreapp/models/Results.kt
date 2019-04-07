package com.leonardis.scoreapp.models
import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("aggregateScore") val aggregateScore: Score? = null,
    @SerializedName("awayTeam") val awayTeam: Team,
    @SerializedName("competitionStage") val competitionStage: CompetitionStage,
    @SerializedName("date") val date: String,
    @SerializedName("homeTeam") val homeTeam: Team,
    @SerializedName("id") val id: Int,
    @SerializedName("penaltyScore") val penaltyScore: Score? = null,
    @SerializedName("score") val score: Score,
    @SerializedName("state") val state: String,
    @SerializedName("type") val type: String,
    @SerializedName("venue") val venue: Venue
)