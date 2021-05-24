package com.example.shopping_list_app.ui.shoppinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem
import com.example.shopping_list_app.shoppingdata.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
): ViewModel() {

    fun upsert(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }
    fun delete(item: ShoppingItem)= CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems()= repository.getAllShoppingItems()
}