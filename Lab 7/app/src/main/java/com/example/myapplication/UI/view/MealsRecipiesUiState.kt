package com.example.myapplication.UI.view

import com.example.myapplication.network.response.RToDoCat

data class MealsRecipiesUiState(
    val recipies: List<RToDoCat>,
    val loading: Boolean = false
)