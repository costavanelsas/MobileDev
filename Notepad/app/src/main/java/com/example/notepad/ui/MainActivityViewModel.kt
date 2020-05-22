package com.example.notepad.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.database.NoteRepository

/**
 * Created by Costa van Elsas on 22-5-2020
 */
class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()
}
