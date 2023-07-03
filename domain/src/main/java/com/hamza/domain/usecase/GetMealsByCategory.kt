package com.hamza.domain.usecase

import com.hamza.domain.entity.MealResponse
import com.hamza.domain.repo.MealsRepo

class GetMealsByCategory(private val mealsRepo: MealsRepo) {

    suspend operator fun invoke(categoryName: String): MealResponse =
        mealsRepo.getMealsByCategory(categoryName)

}