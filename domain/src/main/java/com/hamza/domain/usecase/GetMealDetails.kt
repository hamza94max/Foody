package com.hamza.domain.usecase

import com.hamza.domain.entity.MealsDetailsList
import com.hamza.domain.repo.MealsRepo

class GetMealDetails(private val mealsRepo: MealsRepo) {

    suspend operator fun invoke(mealId: String): MealsDetailsList =
        mealsRepo.getMealDetails(mealId)

}