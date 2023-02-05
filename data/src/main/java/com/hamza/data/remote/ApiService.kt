package com.hamza.data.remote

import com.hamza.domain.entity.CategoryResponse
import retrofit2.http.GET

interface ApiService {

    @GET("categories.php")
    suspend fun getMeals(): CategoryResponse
}