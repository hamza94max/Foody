package com.hamza.Foody.di

import com.hamza.data.remote.ApiService
import com.hamza.data.repo.MealsRepoImpl
import com.hamza.domain.repo.MealsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): MealsRepo {
        return MealsRepoImpl(apiService)
    }


}