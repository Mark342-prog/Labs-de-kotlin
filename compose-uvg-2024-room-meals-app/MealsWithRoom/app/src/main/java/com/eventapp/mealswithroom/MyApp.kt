package com.eventapp.mealswithroom

import android.app.Application
import androidx.room.Room
import com.eventapp.mealswithroom.database.AppDatabase
import com.eventapp.mealswithroom.networking.webservices.MealsWebService
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository

class MyApp : Application() {

    // Singleton instance of the Room database
    private lateinit var database: AppDatabase
        private set

    lateinit var categoryRepository: MealsCategoryRepository
        private set

    lateinit var categoryWebService: MealsWebService
        private set

    override fun onCreate() {
        super.onCreate()

        categoryWebService = MealsWebService()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "meal-categories-db"
        ).build()

        categoryRepository = MealsCategoryRepository(
            categoryWebService,
            database.mealCategoryDao()
        )
    }
}