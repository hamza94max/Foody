package com.hamza.domain.repo

import com.hamza.domain.entity.CategoryResponse

interface MealsRepo {

    suspend fun getDataFromRemote(): CategoryResponse
}