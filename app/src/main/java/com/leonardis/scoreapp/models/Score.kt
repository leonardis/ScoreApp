package com.leonardis.scoreapp.models

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("away") val away: Int,
    @SerializedName("home") val home: Int,
    @SerializedName("winner") val winner: String? = null
)