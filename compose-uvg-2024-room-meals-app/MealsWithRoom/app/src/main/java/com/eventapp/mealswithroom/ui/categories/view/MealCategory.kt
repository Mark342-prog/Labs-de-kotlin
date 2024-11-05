package com.eventapp.mealswithroom.ui.categories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.eventapp.mealswithroom.R
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity
import com.eventapp.mealswithroom.networking.response.categories.Categories
import com.eventapp.mealswithroom.navigation.NavigationState
import com.eventapp.mealswithroom.navigation.navigateTo

@Composable
fun MealCategory(meal: MealCategoryEntity, navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp).clickable {
                        if (meal.name.isNotEmpty()) {
                            navigateTo(
                                navController,
                                NavigationState.MealsRecipesList.createRoute(meal.name)
                            )
                        }
                    }
            ) {
                Text(
                    text = stringResource(R.string.category_name),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.bodySmall
                )
                HorizontalDivider()
                Text(
                    text = stringResource(R.string.date_content, 11, 12),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}