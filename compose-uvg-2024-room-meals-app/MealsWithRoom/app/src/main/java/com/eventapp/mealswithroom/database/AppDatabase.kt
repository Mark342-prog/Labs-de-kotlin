package com.eventapp.mealswithroom.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eventapp.mealswithroom.database.categories.MealCategoryDao
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity

@Database(entities = [MealCategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
}