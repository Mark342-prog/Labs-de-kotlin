package com.example.myapplication.UI.view
    
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.ui.res.stringResource
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
    import androidx.compose.foundation.lazy.grid.items
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Card
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Text
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.lifecycle.viewmodel.compose.viewModel
    import androidx.navigation.NavController
    import coil.compose.AsyncImage
    import com.example.myapplication.R
    import com.example.myapplication.UI.view.navigation.AppBar
    import com.example.myapplication.UI.view.navigation.NavigationState

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MealsRecipiesScreen(
        navController: NavController,
        category: String,
        viewModel: MealsRecipiesViewModel = viewModel()
    ) {
        if(viewModel.recipiesUiState.recipies.isEmpty()){
            viewModel.getRecipiesByCategory(category)
        }
        val waiting = R.string.Waiting
        Scaffold(
            topBar = {
                AppBar(title = "Recipies ($category)", navController = navController)
            }
        ) {
            if (viewModel.recipiesUiState.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(waiting),
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = it,
                ) {
                    items(viewModel.recipiesUiState.recipies) { recipie ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                            onClick = {
                                navController.navigate("${NavigationState.Meals.route}/{$recipie.id}")
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = recipie.name,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                                AsyncImage(
                                    model = recipie.imageUrl,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
