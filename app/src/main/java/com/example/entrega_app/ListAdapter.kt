package com.example.entrega_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entrega_app.ProductDAO
import kotlinx.coroutines.*


class ListAdapter(var productsList: ArrayList<Product>, private val context: Context): RecyclerView.Adapter<com.example.entrega_app.ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productsList[position]
        holder.tv_product.text = product.productName
        holder.tv_quantity.text = product.quantity.toString()
        val productType: String? = product.productType


        // delete action
        holder.btnDelete.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                // Elimina el producto de la base de datos
                AppDatabase.getInstance(context)?.productDAO()?.deleteProductById(product.id!!)
            }

            // Elimina el producto de la lista
            productsList.removeAt(position)

            var product = productsList[position]

            // Notifica al RecyclerView que el elemento ha sido eliminado
            notifyItemRemoved(position)
            // Actualiza los elementos subsiguientes
            notifyItemRangeChanged(position, itemCount)
        }

        // trip to detail view
        holder.tv_product.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra("product",  productsList.get(position))
            context.startActivity(intent)
        }

        // load images into the list
        when(productType){
            "fruit"-> holder.imageProduct.setImageResource(R.drawable.fruta)
            "Meat" -> holder.imageProduct.setImageResource(R.drawable.meat)
            "Vegetable"-> holder.imageProduct.setImageResource(R.drawable.vegetable)
            "Drink" -> holder.imageProduct.setImageResource(R.drawable.drink)
            "Fish"-> holder.imageProduct.setImageResource(R.drawable.fish)
            "Other"-> holder.imageProduct.setImageResource(R.drawable.other)
        }
    }

    override fun getItemCount(): Int {
       return productsList.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tv_product = view.findViewById<TextView>(R.id.product_name_tv)
        val tv_quantity = view.findViewById<TextView>(R.id.quntity_tv_list)
        val btnDelete: Button = view.findViewById(R.id.btn_delete)
        val imageProduct: ImageView = view.findViewById(R.id.image_prod_list)
    }

}

