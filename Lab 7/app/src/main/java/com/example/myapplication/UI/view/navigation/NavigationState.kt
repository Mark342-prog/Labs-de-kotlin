package com.example.myapplication.UI.view.navigation

sealed class NavigationState(val route: String) {
    object Categories: NavigationState("Categories")
    object Meals: NavigationState("Meals")
    object Recipies: NavigationState("Recipies")
}