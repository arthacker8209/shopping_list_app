package com.example.shopping_list_app.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shopping_list_app.R
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem

class AddShoppingItemDialog(context: Context , var addDialogListener: AddDialogListener): AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)

        var itemName: EditText? = findViewById(R.id.item_name)
        var itemAmount: EditText? = findViewById(R.id.item_amount)
        var cancel: TextView? = findViewById(R.id.cancel)
        var save: TextView? = findViewById(R.id.save)

        save?.setOnClickListener {
            val name = itemName?.text.toString()
            val amount = itemAmount?.text.toString()

            if(name.isEmpty() || amount.isEmpty()){
                Toast.makeText(context, "Enter All Information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name , amount.toInt())
            addDialogListener.onSaveButtonClicked(item)
            dismiss()
        }

        cancel?.setOnClickListener {
            cancel()
        }

    }
}