package com.leonardis.scoreapp.models

import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)