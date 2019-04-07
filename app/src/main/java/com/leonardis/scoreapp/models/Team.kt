package com.leonardis.scoreapp.models

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("abbr") val abbr: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("shortName") val shortName: String
)