package com.example.entrega_app

import android.content.Context
import android.util.Log

class ProductProvider {

    companion object{
        private lateinit var db: AppDatabase

        fun initDatabase(context: Context) {
            db = AppDatabase.getInstance(context)!!
        }

        var productList: MutableList<Product> = mutableListOf()

        fun productListSize(): Int{
            return productList.size
        }
        // TODO revisar
        fun addProduct(product: Product){
            productList.add(product)
        }

        fun getProducts(): List<Product>{
            return db.productDAO().loadAllProducts()
        }

        fun setDemoProducts(database: AppDatabase){
            db = database
            db.productDAO().insert(Product(null, "Banana","Fruit", 12.0f, 5))
            db.productDAO().insert(Product(null, "Apple", "Fruit", 3.5f, 5))
            db.productDAO().insert(Product(null, "Rabbbit","Meat", 16.5f, 1) )
            db.productDAO().insert(Product(null, "Cod", "Fish", 24.5f, 1))
            db.productDAO().insert(Product(null, "Coffee", "Other", 24.7f, 1))
            db.productDAO().insert(Product(null, "Prawn", "Fish", 14.4f, 3))
            db.productDAO().insert(Product(null, "Ribeye", "Meat", 45.5f, 1))
            db.productDAO().insert(Product(null, "Combucha", "Drink", 5.5f, 2))

        }
    }
}