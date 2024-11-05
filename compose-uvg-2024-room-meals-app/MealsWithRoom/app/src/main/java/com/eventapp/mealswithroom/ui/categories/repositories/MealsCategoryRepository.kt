package com.eventapp.mealswithroom.ui.categories.repositories

import com.eventapp.mealswithroom.database.categories.MealCategoryDao
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity
import com.eventapp.mealswithroom.networking.webservices.MealsWebService
import com.eventapp.mealswithroom.networking.response.categories.toEntity
import com.eventapp.mealswithroom.networking.webservices.IMealsWebService

class MealsCategoryRepository(private val webService: IMealsWebService,
                              private val mealCategoryDao: MealCategoryDao
) {
    suspend fun getMealsCategories(): List<MealCategoryEntity> {
        val entities = webService.getMealsCategories().categories
        val content = entities.map { it.toEntity() }
        mealCategoryDao.insertAll(content)
        return mealCategoryDao.getAllMealCategories()
    }
}