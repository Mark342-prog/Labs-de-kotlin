package com.example.myapplication.UI.view

import com.example.myapplication.network.response.RToDoCat

data class MealsCategoryUiState(
    val categories: List<RToDoCat>,
    val loading: Boolean = false
)