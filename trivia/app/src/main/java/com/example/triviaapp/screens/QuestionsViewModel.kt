package com.example.triviaapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.data.DataOrException
import com.example.triviaapp.model.QuestionItem
import com.example.triviaapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val repository: QuestionRepository
): ViewModel() {
    private val data : MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception()))

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestion()
            if(data.value.data.toString().isNotEmpty()){
                data.value.loading = false
            }
        }
    }
}