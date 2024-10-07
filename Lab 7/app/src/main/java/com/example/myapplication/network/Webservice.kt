package com.example.myapplication.network

import com.example.myapplication.network.response.MealsCategoriesResponse
import com.example.myapplication.network.response.MealsRecipiesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {
    suspend fun getMeals(): MealsRecipiesResponse {
        return withContext(Dispatchers.IO) {
            webService.getMeals()
        }
    }

    suspend fun getRecipies(category: String): MealsCategoriesResponse {
        return withContext(Dispatchers.IO){
            webService.getRecipiesByCategory(category)
        }
    }
}
