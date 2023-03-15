package com.example.entrega_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        // items from xml
        val textproduct: TextView = findViewById(R.id.product_detail_tv)
        val quantityProduct: TextView = findViewById(R.id.quantity_detail_tv)
        val priceProduct: TextView = findViewById(R.id.price_detail_tv)
        val imageDetail: ImageView = findViewById(R.id.image_detail_iv)


        // get product from main
        val bundle = intent.extras
        val product: Product? = bundle?.getSerializable("product") as Product?

        if (product != null) {
            textproduct.setText(product.productName)
            quantityProduct.setText(product.quantity.toString())
            priceProduct.setText(product.price.toString())

            when(product.productType){
                "fruit"-> imageDetail.setImageResource(R.drawable.fruta)
                "Meat" -> imageDetail.setImageResource(R.drawable.meat)
                "Vegetable"-> imageDetail.setImageResource(R.drawable.vegetable)
                "Drink" -> imageDetail.setImageResource(R.drawable.drink)
                "Fish"-> imageDetail.setImageResource(R.drawable.fish)
                "Other"-> imageDetail.setImageResource(R.drawable.other)
            }
        }
    }
}