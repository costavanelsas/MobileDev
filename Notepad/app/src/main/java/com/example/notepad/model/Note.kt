package com.example.notepad.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Created by Costa van Elsas on 22-5-2020
 */
@Parcelize
@Entity
data class Note (
    var title: String,
    var lastUpdated: Date,
    var text: String,
    @PrimaryKey var id: Long? = null
) : Parcelable