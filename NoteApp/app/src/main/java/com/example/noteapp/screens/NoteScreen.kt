package com.example.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.component.NoteButton
import com.example.noteapp.component.NoteInputText
import com.example.noteapp.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val appContext = LocalContext.current.applicationContext

    Column(
        modifier = Modifier.padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(Icons.Default.Notifications, contentDescription = "Icon")
        }, backgroundColor = Color(0xFFDADFE3))
        Divider(modifier = Modifier.padding(4.dp))
        NoteInputText(text = title, label = "Title", onTextChange = {
            if (it.isValidForNote()) {
                title = it
            }
        })
        NoteInputText(text = description, label = "Add a note", onTextChange = {
            if (it.isValidForNote()) {
                description = it
            }
        })
        NoteButton(text = "Save", onClick = {
            onAddNote(
                Note(
                    title = title,
                    description = description
                )
            )
            title = ""
            description = ""
            Toast.makeText(appContext, "Added note in data", Toast.LENGTH_SHORT).show()
        })

        Divider(modifier = Modifier.padding(4.dp))

        LazyColumn {
            items(notes) { note ->
                NotesRow(note = note) {
                    onRemoveNote(it)
                }
            }
        }
    }
}

@Composable
fun NotesRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .padding(14.dp)
                .clickable { onNoteClicked(note) },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = note.entryTime.toString(),
                style = MaterialTheme.typography.caption
            )
        }
    }
}

private fun String.isValidForNote() = this.all { char ->
    char.isLetter() || char.isWhitespace()
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(emptyList(), {}, {})
}