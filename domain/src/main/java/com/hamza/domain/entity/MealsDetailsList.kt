package com.hamza.domain.entity

import com.google.gson.annotations.SerializedName

data class MealsDetailsList(
    @SerializedName("meals")
    val mealsDetailsList: List<MealDetails>
)
