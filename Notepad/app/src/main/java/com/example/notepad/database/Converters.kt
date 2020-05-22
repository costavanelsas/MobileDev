package com.example.notepad.database

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Costa van Elsas on 22-5-2020
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}