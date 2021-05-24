package com.example.shopping_list_app.ui.shoppinglist

import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem

interface AddDialogListener {

    fun onSaveButtonClicked(item: ShoppingItem)
}