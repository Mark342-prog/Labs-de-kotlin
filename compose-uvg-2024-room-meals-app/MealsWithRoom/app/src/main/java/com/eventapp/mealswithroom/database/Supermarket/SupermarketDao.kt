package com.eventapp.mealswithroom.database.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.eventapp.mealswithroom.database.CartItem


@Dao
interface CartDao {
    @Query("SELECT * FROM cart_item WHERE productId = :productId LIMIT 1")
    suspend fun getCartItem(productId: String): CartItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Update
    suspend fun updateCartItem(cartItem: CartItem)  // Esto debería actualizar `quantity`

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("DELETE FROM cart_item")
    suspend fun clearCart()

    @Query("SELECT * FROM cart_item")
    suspend fun getAllCartItems(): List<CartItem>
}
