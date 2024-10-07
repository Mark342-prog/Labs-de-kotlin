package com.example.myapplication.network.response

import retrofit2.http.GET
import retrofit2.http.Query

interface MealsApi {
    @GET("categories.php")
    suspend fun RToDoCat(category: String): MealsCategoriesResponse
    @GET("filter.php")
    suspend fun RecipieRes(@Query("c") category: String): MealsRecipiesResponse
    abstract fun RecipieRes(): MealsRecipiesResponse
}
