package com.eventapp.mealswithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.eventapp.mealswithroom.navigation.Navigation
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealViewModelFactory
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme

class MainActivity : ComponentActivity() {

    private lateinit var mealViewModel: MealsCategoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (applicationContext as MyApp).categoryRepository
        mealViewModel = ViewModelProvider(
            this,
            MealViewModelFactory(repository))[MealsCategoriesViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            MealsWithRoomTheme {
                Navigation(navController = rememberNavController(),
                    mealViewModel = mealViewModel)
            }
        }
    }
}