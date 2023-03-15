package com.example.entrega_app

import java.io.Serializable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")

data class Product (
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "productName") var productName: String?,
    @ColumnInfo(name = "productType") var productType: String?,
    @ColumnInfo(name = "price") var price: Float?,
    @ColumnInfo(name = "quantity") var quantity: Int?
)