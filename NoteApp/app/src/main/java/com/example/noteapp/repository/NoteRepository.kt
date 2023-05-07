package com.example.noteapp.repository

import com.example.noteapp.data.NoteDatabaseDao
import com.example.noteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.UUID
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabaseDao: NoteDatabaseDao
) {
    fun getNotes(): Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
    suspend fun getNoteById(noteId: UUID) = noteDatabaseDao.getNoteById(noteId)
    suspend fun insertNote(note: Note) = noteDatabaseDao.insertNote(note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    suspend fun deleteAll() = noteDatabaseDao.deleteAll()
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
}