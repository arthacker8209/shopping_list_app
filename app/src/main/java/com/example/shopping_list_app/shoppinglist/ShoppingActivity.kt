package com.example.shopping_list_app.ui.shoppinglist

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.R
import com.example.shopping_list_app.other.ShoppingItemAdapter
import com.example.shopping_list_app.shoppingdata.db.ShoppingDataBase
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem
import com.example.shopping_list_app.shoppingdata.repositories.ShoppingRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ShoppingActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        val recyclerView:RecyclerView?= findViewById(R.id.rv_shoppingItems)
        val floatingActionButton: FloatingActionButton?=findViewById(R.id.add)
        val database= ShoppingDataBase(this)
        val respository= ShoppingRepository(database)
        val factory= ShoppingViewModelFactory(respository)
        val viewModel= ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter=ShoppingItemAdapter(listOf(), viewModel)
        recyclerView?.layoutManager= LinearLayoutManager(this)
        recyclerView?.adapter= adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items= it
            adapter.notifyDataSetChanged()
        })

        floatingActionButton?.setOnClickListener {
            AddShoppingItemDialog(this, object : AddDialogListener{
                override fun onSaveButtonClicked(item: ShoppingItem) {
                  viewModel.upsert(item)
                }
            }).show()
        }
    }
}