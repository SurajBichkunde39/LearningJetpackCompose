package com.example.noteapp.data

import com.example.noteapp.model.Note

class NotesDataSource {

    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "Sample Note1", description = "Sample Note1 description"),
            Note(title = "Sample Note2", description = "Sample Note2 description"),
            Note(title = "Sample Note3", description = "Sample Note3 description"),
            Note(title = "Sample Note4", description = "Sample Note4 description"),
            Note(title = "Sample Note5", description = "Sample Note5 description"),
            Note(title = "Sample Note6", description = "Sample Note6 description")
        )
    }
}