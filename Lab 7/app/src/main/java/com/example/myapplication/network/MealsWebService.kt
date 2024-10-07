package com.example.myapplication.network
import com.example.myapplication.network.response.MealsApi
import com.example.myapplication.network.response.MealsCategoriesResponse
import com.example.myapplication.network.response.MealsRecipiesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealsWebService {

    private val api: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(MealsApi::class.java)
    }

    suspend fun getMeals(): MealsRecipiesResponse {
        return api.RecipieRes()
    }
    suspend fun getRecipiesByCategory(category: String): MealsCategoriesResponse {
        return api.RToDoCat(category)
    }
}