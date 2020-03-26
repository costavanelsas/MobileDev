package com.example.level_3_task_example

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(
    var reminderText: String
) : Parcelable