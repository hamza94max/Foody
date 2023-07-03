package com.hamza.Foody.ui.HomeFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamza.domain.entity.CategoryResponse
import com.hamza.domain.usecase.GetCategories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getMealsUseCase: GetCategories
) : ViewModel() {

    private val _categories: MutableStateFlow<CategoryResponse?> = MutableStateFlow(null)
    var categories: MutableStateFlow<CategoryResponse?> = _categories


    fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.value = getMealsUseCase()
                Log.i("hamza", _categories.value.toString())
            } catch (e: Exception) {
                Log.i("hamza", e.message.toString())
            }
        }
    }


}