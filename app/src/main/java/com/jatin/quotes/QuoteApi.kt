package com.jatin.quotes

import retrofit2.http.GET

interface QuoteApi {
    @GET("/random")
    suspend fun getRandomQuote(): Quote
}