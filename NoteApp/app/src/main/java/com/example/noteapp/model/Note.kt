package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "note_title")
    val title: String,
    @ColumnInfo(name = "note_description")
    val description: String,
    @ColumnInfo(name = "note_entry_date")
    val entryTime: String = Instant.now().toString()
)
