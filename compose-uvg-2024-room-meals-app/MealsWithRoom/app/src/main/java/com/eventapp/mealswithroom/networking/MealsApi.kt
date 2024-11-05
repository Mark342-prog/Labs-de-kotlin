package com.eventapp.mealswithroom.networking

import com.eventapp.mealswithroom.networking.response.categories.MealsCategoriesResponse
import com.eventapp.mealswithroom.networking.response.meals.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {

    @GET("categories.php")
    suspend fun getMealsCategories(): MealsCategoriesResponse

    @GET("filter.php")
    suspend fun filterByCategory(
        @Query("c") category: String
    ): MealsResponse
}