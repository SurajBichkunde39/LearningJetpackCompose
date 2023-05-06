package com.example.noteapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            if(it.isValidForNote()) {
                title = it
            }
        })
        NoteInputText(text = description, label = "Add a note", onTextChange = {
            if(it.isValidForNote()) {
                description = it
            }
        })
        NoteButton(text = "Save", onClick = {
            // Save the data
            title = ""
            description = ""
        })
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