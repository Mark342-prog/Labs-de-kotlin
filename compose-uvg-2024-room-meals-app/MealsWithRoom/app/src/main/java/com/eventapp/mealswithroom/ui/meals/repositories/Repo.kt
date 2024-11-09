package com.eventapp.mealswithroom.ui.meals.repositories

import com.eventapp.mealswithroom.database.CartItem
import com.eventapp.mealswithroom.database.categories.CartDao


class CartRepository(private val cartDao: CartDao) {

    suspend fun addItemToCart(productId: String, productName: String, price: Double, quantity: Int) {
        val existingItem = cartDao.getCartItem(productId)
        if (existingItem == null) {
            // Si el producto no está en el carrito, lo insertamos con la cantidad inicial
            val cartItem = CartItem(productId, productName, quantity, price)
            cartDao.insertCartItem(cartItem)
        } else {
            // Si ya existe, actualizamos la cantidad sumando la nueva cantidad
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + quantity)
            cartDao.updateCartItem(updatedItem)
        }
    }

    suspend fun updateItemQuantity(productId: String, quantity: Int) {
        val existingItem = cartDao.getCartItem(productId)
        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = quantity)
            cartDao.updateCartItem(updatedItem)
        }
    }

    suspend fun removeItemFromCart(productId: String) {
        val existingItem = cartDao.getCartItem(productId)
        if (existingItem != null) {
            cartDao.deleteCartItem(existingItem)
        }
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }

    suspend fun getCartItems(): List<CartItem> = cartDao.getAllCartItems()

    suspend fun getCartTotalPrice(): Double {
        return cartDao.getAllCartItems().sumByDouble { it.totalPrice }
    }
}
