package com.example.shopping_list_app.shoppingdata.repositories

import androidx.lifecycle.LiveData
import com.example.shopping_list_app.shoppingdata.db.ShoppingDataBase
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem

class ShoppingRepository(
    private val db:ShoppingDataBase
) {
 suspend fun upsert(item : ShoppingItem)= db.getShoppingDao().upsert(item)
    suspend fun delete(item: ShoppingItem)= db.getShoppingDao().delete(item)
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>> = db.getShoppingDao().getAllShoppingItems()
}