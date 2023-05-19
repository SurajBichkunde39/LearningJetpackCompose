package com.example.triviaapp.network

import com.example.triviaapp.model.QuestionItemList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionAPI {
    @GET("world.json")
    suspend fun getAllQuestions(): QuestionItemList
}