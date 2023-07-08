package com.hamza.Foody.ui.DetailsFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hamza.domain.entity.MealsDetailsList
import com.hamza.domain.usecase.GetMealDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMealDetailsUseCase: GetMealDetails
) : ViewModel() {

    private val _mealDetails: MutableStateFlow<MealsDetailsList?> = MutableStateFlow(null)
    var mealDetails: LiveData<MealsDetailsList?> = _mealDetails.asLiveData()

    fun getMealDetails(mealId: String) {
        viewModelScope.launch {
            try {
                _mealDetails.value = getMealDetailsUseCase(mealId)
            } catch (e: Exception) {
                Log.i("hamzaD", e.message.toString())
            }
        }
    }


}