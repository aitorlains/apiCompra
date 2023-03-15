package com.example.entrega_app

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CreateActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        // items from xml
        val product_name: EditText = findViewById(R.id.prodName_et)
        val price: EditText = findViewById(R.id.productPrice_et)
        val btn_inc: Button = findViewById(R.id.increase_btn)
        val btn_dec: Button = findViewById(R.id.decrease_btn)
        val quantityDisplay: TextView = findViewById(R.id.quantity_tv)
        val btn_add: Button = findViewById(R.id.add_btn)
        val image: ImageView = findViewById(R.id.imageView)

        // spinner
        val productTypes = resources.getStringArray(R.array.Languages)
        val spinner_sp: Spinner = findViewById(R.id.spinner_view)
        var productTypeBuffer = ""

        val adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, productTypes)
        spinner_sp.adapter = adapter

        spinner_sp.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>,view: View, position: Int, id: Long) {

                when(position){

                    0 -> {image.setImageDrawable(setImageByCategory("fruta"))
                        productTypeBuffer = resources.getString(R.string.fruit)
                        toast(productTypeBuffer)
                    }

                    1 -> {
                            image.setImageDrawable(setImageByCategory("meat"))
                            productTypeBuffer = resources.getString(R.string.Meat)
                            toast(productTypeBuffer)
                    }

                    2 -> {
                        image.setImageDrawable(setImageByCategory("vegetable"))
                        productTypeBuffer = resources.getString(R.string.Vegetable)
                        toast(productTypeBuffer)
                    }
                    3 -> {
                        image.setImageDrawable(setImageByCategory("drink"))
                        productTypeBuffer = resources.getString(R.string.Drink)
                        toast(productTypeBuffer)
                    }
                    4 -> {
                        image.setImageDrawable(setImageByCategory("fish"))
                        productTypeBuffer = resources.getString(R.string.Fish)
                        toast(productTypeBuffer)
                    }

                    5 -> {
                        image.setImageDrawable(setImageByCategory("other"))
                        productTypeBuffer = resources.getString(R.string.Other)
                        toast(productTypeBuffer)
                    }

                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                toast("you must select something")
            }
        }

        // buffers class
        var textName = ""
        var priceProduct: Float = 0.0f
        var quantity: Int = 1

        // check list
        var priceFlag: Boolean = false
        var productNameFlag: Boolean = false
        var quantityFlag: Boolean = false


        // By default we show 1 ud
        quantityDisplay.setText(quantity.toString())

        // by default image
        image.setImageResource(getResources().getIdentifier("icon_minus", "drawable", getPackageName()));
        // minimum quantity should be above of 0
        btn_dec.setOnClickListener{

            if (quantity == 0){
                toast("Cannot be under 0")
            }else{
               --quantity
                quantityDisplay.setText(quantity.toString())
                quantityFlag = true
            }
        }

        btn_inc.setOnClickListener{
                ++quantity
                quantityDisplay.setText(quantity.toString())
                quantityFlag = true
        }

       btn_add.setOnClickListener {
           // check if product name is empty
           if (product_name.text.isNotBlank()){
               textName = product_name.text.toString()
               productNameFlag = true

           }else{
               toast("Name product is required")
           }

           // check if price is empty
           if (price.text.isNotBlank()){
              priceProduct = price.text.toString().toFloat()

               // check if price is negative value
               if(priceProduct < 0){
                  toast("price cannot be negative value")
               }else{ priceFlag = true }

           }else{ toast("price is required") }

            // check if every data as been inserted correctly
           if (productNameFlag && priceFlag){
               var product = Product(1, textName, productTypeBuffer, priceProduct, quantity)
               toast("New product successfully created")

               val intent = Intent(this, MainActivity::class.java).apply {
                  // putExtra("product", product)

               }
               startActivity(intent)
           }
       }
    }

    fun toast(text: String){

        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    fun setImageByCategory( uri: String ) : Drawable{
        val imageResource: Int = resources.getIdentifier("@drawable/$uri", null, packageName);
        return resources.getDrawable(imageResource)
    }
}