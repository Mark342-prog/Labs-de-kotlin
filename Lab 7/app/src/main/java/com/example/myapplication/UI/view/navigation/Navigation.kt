package com.example.myapplication.UI.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.UI.view.MealsCategoriesScreen
import com.example.myapplication.UI.view.MealsRecipiesScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationState.Categories.route,
        modifier = modifier
    ) {
        composable(route = NavigationState.Categories.route) {
            MealsCategoriesScreen(navController)
        }
        composable(route = NavigationState.Meals.route + "/{category}") { backstackEntry ->
            MealsRecipiesScreen(navController, backstackEntry.arguments?.getString("category") ?: "")
        }
    }
}