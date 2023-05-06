package com.example.noteapp.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note

class NoteViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun getAllNotes(): List<Note> = noteList
    fun addNote(note: Note) = noteList.add(note)
    fun removeNote(note: Note) = noteList.remove(note)
}