package com.example.noteapp.data

import androidx.room.*
import com.example.noteapp.model.Note
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM note_table")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE id=:noteId")
    suspend fun getNoteById(noteId: UUID): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note: Note)
}
