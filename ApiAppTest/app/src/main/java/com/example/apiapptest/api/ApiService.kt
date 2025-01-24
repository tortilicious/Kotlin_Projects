package com.example.apiapptest.api

import com.example.apiapptest.model.RespuestaSeries
import retrofit2.http.GET

interface ApiService {
    @GET("series")
    suspend fun getSeries(): RespuestaSeries
}