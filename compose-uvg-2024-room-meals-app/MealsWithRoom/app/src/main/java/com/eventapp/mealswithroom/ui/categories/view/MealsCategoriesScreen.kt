package com.eventapp.mealswithroom.ui.categories.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eventapp.mealswithroom.navigation.AppBar
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MealsCategoriesScreen(navController: NavController,
                          viewModel: MealsCategoriesViewModel) {
    val categories = viewModel.categories.observeAsState(initial = emptyList())
    val isLoading = viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }

    errorMessage?.let {
        Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
    }

    Scaffold(topBar = {
        AppBar(title = "Recipes", navController = navController)
    }) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (isLoading.value) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(categories.value) { category ->
                        MealCategory(category, navController)
                    }
                }
            }
        }
    }
}