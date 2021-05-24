package com.example.shopping_list_app.shoppingdata.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun upsert (item : ShoppingItem)

    @Delete
   suspend fun delete(item : ShoppingItem)

   @Query("SELECT * FROM shopping_items")
   fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
}