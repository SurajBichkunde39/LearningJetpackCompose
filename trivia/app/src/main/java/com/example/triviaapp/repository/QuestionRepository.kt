package com.example.triviaapp.repository

import android.util.Log
import com.example.triviaapp.data.DataOrException
import com.example.triviaapp.model.QuestionItem
import com.example.triviaapp.network.QuestionAPI
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionAPI
) {
    private var dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestion(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try{
            dataOrException.loading = true
            dataOrException.data = api.getAllQuestions().questionItemList
            if(dataOrException.data.toString().isNotEmpty()){
                dataOrException.loading = false
            }
        } catch (exception: Exception) {
            dataOrException.e = exception
            Log.d("Exec", "getAllQuestion issue ${exception.localizedMessage}")
        }
        return dataOrException
    }
}