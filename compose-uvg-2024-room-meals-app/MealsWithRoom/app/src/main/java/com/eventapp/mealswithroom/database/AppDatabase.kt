package com.eventapp.mealswithroom.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import com.eventapp.mealswithroom.database.categories.CartDao
import com.eventapp.mealswithroom.database.categories.MealCategoryDao
import com.eventapp.mealswithroom.database.categories.MealCategoryEntity

@Database(entities = [MealCategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun mealCategoryDao(): MealCategoryDao
}
@Database(entities = [CartItem::class], version = 1)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun cartDao(): CartDao
    // Aquí también incluirías otros DAOs como MealCategoryDao o MealDao si es necesario.
}
@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey val productId: String,
    val productName: String,
    var quantity: Int,  // `quantity` debe ser `var` para permitir modificaciones
    val price: Double
) {
    val totalPrice: Double
        get() = quantity * price
}