package com.example.entrega_app
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query

@Dao
interface ProductDAO {

    @Insert(onConflict = REPLACE)
    fun insert(product: Product)

    @Query("SELECT * FROM products")
    fun loadAllProducts(): List<Product>

    @Query("SELECT COUNT(*) FROM products")
    fun countProdcuts(): Int

    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id: Int)

}