package com.hamza.Foody.ui.FilterCategoriesFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hamza.domain.entity.MealResponse
import com.hamza.domain.usecase.GetMealsByCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val getMealsByCategory: GetMealsByCategory
) : ViewModel() {

    private val _meals: MutableStateFlow<MealResponse?> = MutableStateFlow(null)
    var meals: LiveData<MealResponse?> = _meals.asLiveData()


    fun getMeals(categoryName: String) {
        viewModelScope.launch {
            try {
                _meals.value = getMealsByCategory(categoryName)
            } catch (e: Exception) {
                Log.i("hamzafilter", e.message.toString())
            }
        }
    }


}