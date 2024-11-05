package com.eventapp.mealswithroom.database.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.google.android.gms.tasks.Continuation


@Dao
interface CartDao {
    @Query("SELECT * FROM supermarket_basket")
    suspend fun getAllCartItems(): List<SupermarketEntity>

    @Query("SELECT * FROM supermarket_basket WHERE id = :productId LIMIT 1")
    suspend fun getCartItem(productId: String): SupermarketEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: SupermarketEntity)

    @Update
    suspend fun updateCartItem(cartItem: SupermarketEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: SupermarketEntity)

    @Query("DELETE FROM supermarket_basket")
    suspend fun clearCart()
}