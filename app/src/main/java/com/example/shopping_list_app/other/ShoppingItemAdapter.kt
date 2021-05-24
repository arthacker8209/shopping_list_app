package com.example.shopping_list_app.other

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping_list_app.R
import com.example.shopping_list_app.shoppingdata.db.entities.ShoppingItem
import com.example.shopping_list_app.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
): RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.shopping_list,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        var currentShoppingItem = items[position]
        holder.itemName.text= currentShoppingItem.name
        holder.itemAmount.text= currentShoppingItem.amount.toString()
        holder.delete.setOnClickListener {
            viewModel.delete(currentShoppingItem)
        }
        holder.plus.setOnClickListener {
            currentShoppingItem.amount++
            viewModel.upsert(currentShoppingItem)
        }
        holder.minus.setOnClickListener {
            if(currentShoppingItem.amount>0){
                currentShoppingItem.amount--
                viewModel.upsert(currentShoppingItem)
            }
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
         var itemName: TextView = itemView.findViewById(R.id.tv_Item)
        var itemAmount: TextView = itemView.findViewById(R.id.tv_Amount)
        var delete : ImageView = itemView.findViewById(R.id.delete)
        var minus : ImageView = itemView.findViewById(R.id.minus)
        var plus : ImageView = itemView.findViewById(R.id.plus)

    }
}