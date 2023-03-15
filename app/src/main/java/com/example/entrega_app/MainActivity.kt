package com.example.entrega_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // declaramos esto para poder usar la base de datos
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // vínculo con el xml
        setContentView(R.layout.activity_main)

        // boton para navegar a la view create product
        val createBtn: Button = findViewById(R.id.create_btn)

        // esto es una corrutine para cargar los datos a la base de datos
        GlobalScope.launch {
            //db = AppDatabase.getInstance(applicationContext)!!
            db = AppDatabase.getInstance(applicationContext)!!
            ProductProvider.initDatabase(applicationContext)

            // cargamos los productos de la base de datos
            val products = ProductProvider.getProducts()

            // comprobamos si la base de datos está vacía
            if (products.isEmpty()) {
                // si está vacía seteamos unos de muestra
                ProductProvider.setDemoProducts(db)
                Log.i("/-->", "DDBB Empty")
            }

            ProductProvider.productList = products.toMutableList()

            ProductProvider.productList.forEach {
                Log.i("/-->", it.toString())
            }

        }

        //addProduct()
        viewCOnfig()

        createBtn.setOnClickListener{
             val intent = Intent(this@MainActivity, CreateActivity::class.java).apply {
                 Log.i("--->", String().toString())
             }

            startActivity(intent)

        }
    }

    private fun viewCOnfig(){
        val rvList = findViewById<RecyclerView>(R.id.product_rv)
        rvList.adapter = ListAdapter( ProductProvider.productList as ArrayList<Product>, this)
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun addProduct() {
        val bundle = intent.extras
        val product: Product? = bundle?.getSerializable("product") as Product?


        if (product != null) {
            product.id =  ProductProvider.productList.size+1
            ProductProvider.productList.add(product)
        }
    }
}