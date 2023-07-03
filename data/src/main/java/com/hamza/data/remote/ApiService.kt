package com.hamza.data.remote

import com.hamza.domain.entity.CategoryResponse
import com.hamza.domain.entity.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse


    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealResponse

}