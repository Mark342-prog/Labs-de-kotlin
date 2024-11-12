package com.eventapp.mealswithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.eventapp.mealswithroom.navigation.Navigation
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository
import com.eventapp.mealswithroom.ui.categories.view.CameraScreen
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealViewModelFactory
import com.eventapp.mealswithroom.ui.categories.viewmodel.MealsCategoriesViewModel
import com.eventapp.mealswithroom.ui.testui.CameraXExampleTheme
import com.eventapp.mealswithroom.ui.theme.MealsWithRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraXExampleTheme {
                // Pantalla de cámara en Compose
                Surface(color = MaterialTheme.colorScheme.background) {
                    CameraScreen()
                }
            }
        }
    }
}