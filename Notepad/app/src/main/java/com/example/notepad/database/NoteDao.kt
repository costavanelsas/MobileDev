package com.example.notepad.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notepad.model.Note

/**
 * Created by Costa van Elsas on 22-5-2020
 */
@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(note: Note)

    @Query("SELECT * FROM Note LIMIT 1")
    fun getNotepad(): LiveData<Note?>

    @Update
    suspend fun updateNote(note: Note)
}