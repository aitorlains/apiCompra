package com.example.entrega_app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Product::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "products.db").build()
                } }

            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        fun deleteAllProductsFromDDBB(){
            val clearAllTables = INSTANCE?.clearAllTables()
        }
    }
}