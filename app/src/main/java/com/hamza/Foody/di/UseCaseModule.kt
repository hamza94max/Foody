package com.hamza.Foody.di

import com.hamza.domain.repo.MealsRepo
import com.hamza.domain.usecase.GetCategories
import com.hamza.domain.usecase.GetMealsByCategory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {


    @Provides
    fun provideMealsUseCase(mealsRepo: MealsRepo): GetCategories {
        return GetCategories(mealsRepo)
    }

    @Provides
    fun provideMealsByCategoryUseCase(mealsRepo: MealsRepo): GetMealsByCategory {
        return GetMealsByCategory(mealsRepo)
    }
}