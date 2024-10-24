package com.makelick.numbermagic.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApi {

    @GET("{number}")
    suspend fun getNumberFact(@Path("number") num: Int): String

    @GET("random/math")
    suspend fun getRandomFact(): String

    companion object {
        const val BASE_URL = "http://numbersapi.com/"
    }
}