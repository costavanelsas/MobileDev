package com.example.level_6_example

import com.google.gson.annotations.SerializedName

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
data class Trivia(
    @SerializedName("text") var text: String,
    @SerializedName("number") var number: Int,
    @SerializedName("found") var found: Boolean,
    @SerializedName("type") var type: String
)
