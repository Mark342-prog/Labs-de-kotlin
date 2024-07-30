package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

interface Dessert {
    fun eat() //Es abstracto
}
open class Food (val Name:String, val Price:Int)
{
    open fun cook() = "Se Hornea, de alguna manera pero lo hace"
}
class hamburger():Food("Hamburger", 20){
    override fun cook() = "Se hace todo en un asador"
}
class pizza():Food("pizza", 25){
    override fun cook() = "Se hace todo en un horno"
}
class Icecream(): Food("Icecream", 10){
    override fun cook() = "Se mete al conjelador"
    fun eat() = "Se come el helado"
}
abstract class Drink : Food("", 0){
    abstract fun pour(): String
}
class Juice(): Drink() {
    override fun pour() = "Se vierte en un vaso"
    override fun cook() = "Se exprime el jugo"
}
fun Food.discountedPrice( i :Int): Int {
    return (Price%i)
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val Cessar = pizza()
        Log.i("Pizza", Cessar.cook())
        val woper = hamburger()
        Log.i("hambuerguesa",woper.cook())
        val naranja = Juice()
        Log.i("Jugo", naranja.cook())
        Log.i("Jugo", naranja.pour())
        val fresa = Icecream()
        Log.i("Helado", fresa.eat())
        Log.i("Helado", fresa.cook())



    }
}