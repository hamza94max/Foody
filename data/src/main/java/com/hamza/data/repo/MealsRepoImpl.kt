package com.hamza.data.repo

import com.hamza.data.remote.ApiService
import com.hamza.domain.entity.CategoryResponse
import com.hamza.domain.entity.MealResponse
import com.hamza.domain.repo.MealsRepo

class MealsRepoImpl(private val apiService: ApiService) : MealsRepo {

    override suspend fun getCategoriesFromRemote(): CategoryResponse = apiService.getCategories()

    override suspend fun getMealsByCategory(categoryName: String): MealResponse =
        apiService.getMealsByCategory(categoryName)


}