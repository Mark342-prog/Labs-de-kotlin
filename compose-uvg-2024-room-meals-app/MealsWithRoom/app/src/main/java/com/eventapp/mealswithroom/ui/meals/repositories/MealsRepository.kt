package com.eventapp.mealswithroom.ui.meals.repositories

import com.eventapp.mealswithroom.networking.webservices.MealsWebService
import com.eventapp.mealswithroom.networking.response.meals.Meal

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    suspend fun filterByCategory(category: String): List<Meal> {
        return webService.filterMealsByCategory(category).meals
    }
}