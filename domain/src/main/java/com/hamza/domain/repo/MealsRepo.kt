package com.hamza.domain.repo

import com.hamza.domain.entity.CategoryResponse
import com.hamza.domain.entity.MealResponse

interface MealsRepo {

    suspend fun getCategoriesFromRemote(): CategoryResponse

    suspend fun getMealsByCategory(categoryName: String): MealResponse
}