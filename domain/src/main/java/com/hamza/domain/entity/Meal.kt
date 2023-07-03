package com.hamza.domain.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meal(
    @SerializedName("idMeal")
    val mealId: String? = "no id ",

    @SerializedName("strMeal")
    val mealName: String? = " no name ",

    @SerializedName("strMealThumb")
    val imageOfMeal: String? = " no image",
) : Serializable
