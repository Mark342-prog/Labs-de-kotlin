package com.example.myapplication

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.Snippets.LazyColumn

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val names = listOf("Kaylie Grant",
            "Leon Ventura",
            "Zora Parker",
            "Caleb Porter",
            "Ryleigh Santiago",
            "Beckham Mills",
            "June Berg",
            "Cayson Malone",
            "Skyler Daniels",
            "Xander Austin",
            "Alivia Ruiz",
            "Austin Terrell",
            "Paityn Hughes",
            "Everett Mahoney",
            "Promise Patton",
            "Moises Schmidt",
            "Kimberly Mueller",
            "Albert Hunt",
            "Genevieve Silva",
            "Luka Bowman",
            "Fiona Bennett",
            "Leonardo Eaton",
            "Miley Henderson",
            "Beau Gilbert",
            "Jocelyn Beck",
            "Eduardo Hickman",
            "Scarlette Whitehead",
            "Zayd Strong",
            "Margo Miranda",
            "Rory Garner",
            "Jacqueline Barton",
            "Cassius Coffey",
            "Paola Barr",
            "Harley Houston",
            "Lylah Walls",
            "Larry O’Neill",
            "Kenna Hardin",
            "Hassan Jordan",
            "Adalynn Monroe",
            "Colby Sloan",
            "Selene Harris",
            "Samuel Conrad",
            "Bexley Melton",
            "Lennon Wu",
            "Liana Mora",
            "Arturo Becker",
            "Laura O’Neal",
            "Eddie Lloyd",
            "Emely Cook",
            "Ezekiel Harris",
            "Penelope Montes",
            "Darren Duran",
            "Willa Sutton",
            "Warren Cisneros",
            "Janelle Manning",
            "Seth Duke",
            "Melani Brady",
            "Reed Griffith",
            "Alicia Hodges").groupBy { it.first() }.toSortedMap()
        val namesList = names.map {
            Category(
                name = it.key.toString(),
                items = it.value
            )
        }

        setContent {
            MyApplicationTheme {
                Snippets.SimpleFilledTextFieldSample()
                Snippets.SimpleFilledTextFieldSample2()
                Snippets.FilledButton {}
                CategorizedLazyColumn(
                    categories = namesList
                )


            }
        }
    }
}
data class Category(
    val name: String,
    val items: List<String>
)
@Composable
private fun CategoryHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp)
    )
}

@Composable
private fun CategoryItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        categories.forEach { category ->
            stickyHeader {
                CategoryHeader(category.name)
            }
            items(category.items) { text ->
                CategoryItem(text)
            }
        }
    }
}

private object Snippets {
    @Composable
    fun SimpleFilledTextFieldSample() {
        var text by remember { mutableStateOf("Ej: Carne") }

        TextField(
            modifier = Modifier
                .padding(all = 10.dp)
                .padding(start = 10.dp)
                .padding(end = 10.dp)
                .padding(top = 60.dp)
                .fillMaxWidth(1f),

            value = text,
            onValueChange = { text = it },
            label = { Text("Receta") }
        )
    }


@Composable
fun SimpleFilledTextFieldSample2() {
    var text by remember { mutableStateOf("URL foto") }

    TextField(
        modifier = Modifier
            .padding(all = 10.dp)
            .padding(start = 10.dp)
            .padding(end = 10.dp)
            .padding(top = 140.dp)
            .fillMaxWidth(1f),

        value = text,
        onValueChange = { text = it },
        label = { Text("https://??") }
    )
}
    @Composable
    fun FilledButton(onClick: () -> Unit) {
        Button(onClick = { onClick() }, modifier = Modifier
            .padding(top = 260.dp)
            .padding(start = 180.dp)
            .height(80.dp)) {
            Text("Agregar")

        }
    }
    @Composable
    fun LazyColumn(
        modifier: Modifier = Modifier,
        state: LazyListState = rememberLazyListState(),
        contentPadding: PaddingValues = PaddingValues(0.dp),
        reverseLayout: Boolean = false,
        verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
        horizontalAlignment: Alignment.Horizontal = Alignment.Start,
        flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
        userScrollEnabled: Boolean = true,
        content: LazyListScope.() -> Unit
    ): Unit {}
    @Composable
    fun LazyVerticalStaggeredGrid(){}

}
data class Item(val title: String, val description: String)
