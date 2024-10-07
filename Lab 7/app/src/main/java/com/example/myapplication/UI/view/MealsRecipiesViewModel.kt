package com.example.myapplication.UI.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.network.MealsRepository
import kotlinx.coroutines.launch

class MealsRecipiesViewModel(private val repository: MealsRepository = MealsRepository()): ViewModel() {

    var recipiesUiState by mutableStateOf(MealsRecipiesUiState(emptyList(), loading = false))
        private set

    fun getRecipiesByCategory(category: String) {
        recipiesUiState = MealsRecipiesUiState(emptyList(), loading = true)

        viewModelScope.launch {
            try {

                val recipiesResponse = repository.getRecipies(category)


                recipiesUiState = MealsRecipiesUiState(
                    recipies = recipiesResponse.categories,
                    loading = false
                )
            } catch (e: Exception) {

                recipiesUiState = MealsRecipiesUiState(
                    recipies = emptyList(),
                    loading = false
                )
            }
        }
    }
}