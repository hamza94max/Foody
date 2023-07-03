package com.hamza.domain.usecase

import com.hamza.domain.repo.MealsRepo

class GetCategories(private val mealsRepo: MealsRepo) {

    suspend operator fun invoke() = mealsRepo.getCategoriesFromRemote()
}