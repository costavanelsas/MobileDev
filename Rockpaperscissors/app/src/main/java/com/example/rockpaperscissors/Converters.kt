package com.example.rockpaperscissors

import android.annotation.TargetApi
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.rockpaperscissors.model.GameEnum
import java.time.LocalDateTime

/**
 * class for the type converters
 * source: https://developer.android.com/training/data-storage/room/referencing-data
 */
class Converters {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toDate(dateString: String?): LocalDateTime? {
        return if (dateString == null) {
            null
        } else {
            LocalDateTime.parse(dateString)
        }
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime?): String? {
        return date?.toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal
    private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]

    @TypeConverter
    fun myEnumToTnt(value: GameEnum) = value.toInt()

    @TypeConverter
    fun intToMyEnum(value: Int) = value.toEnum<GameEnum>()
}