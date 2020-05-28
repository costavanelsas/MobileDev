package com.example.advancedrv

/**
 * Created by Costa van Elsas on 28-5-2020.
 */
data class ColorItem(
    var hex: String,
    var name: String
) {
    fun getImageUrl() = "http://singlecolorimage.com/get/$hex/1080x1080"
}
