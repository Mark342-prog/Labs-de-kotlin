package com.example.myapplication.UI.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.MealsRepository
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {

    var categoryUiState by mutableStateOf(MealsCategoryUiState(emptyList()))
        private set

    fun getMeals(category: String) {

        categoryUiState = MealsCategoryUiState(emptyList(), loading = true)

        viewModelScope.launch {

            categoryUiState = MealsCategoryUiState(
                categories = repository.getRecipies(category).categories
            )
        }
    }

}