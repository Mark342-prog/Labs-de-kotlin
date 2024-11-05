package com.eventapp.mealswithroom.navigation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.eventapp.mealswithroom.ui.categories.view.MealsCategoriesScreen
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.eventapp.mealswithroom.ui.meals.view.MealsFilterScreen

@Composable
fun Navigation(navController: NavHostController, mealViewModel: MealsCategoriesViewModel, modifier: Modifier = Modifier) {
    NavHost(navController = navController,
        startDestination = NavigationState.MealsCategories.route,
        modifier = modifier) {

        composable(route = NavigationState.MealsCategories.route) {
            MealsCategoriesScreen(navController = navController,
                viewModel = mealViewModel)
        }
        composable(NavigationState.MealsRecipesList.route,
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val categoryName = arguments.getString("category") ?: ""
            Log.d("ARGUMENTNAV", categoryName)
            MealsFilterScreen(navController = navController, category = categoryName)
        }
        composable(route = NavigationState.Home.route) {
            MealsFilterScreen(navController = navController, category = "Chicken")
        }
        composable(route = NavigationState.Profile.route) {
            //Screen of Profile
        }
    }
}