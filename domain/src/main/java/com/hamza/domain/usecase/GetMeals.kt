package com.hamza.domain.usecase

import com.hamza.domain.repo.MealsRepo

class GetMeals(private val mealsRepo: MealsRepo) {
    
    suspend operator fun invoke() = mealsRepo.getDataFromRemote()
}