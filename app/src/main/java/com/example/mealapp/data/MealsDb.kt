package com.example.mealapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Meal::class], version = 3, exportSchema = false)
abstract class MealsDb : RoomDatabase() {
    abstract fun mealDao(): MealsDao

    companion object {
        @Volatile
        private var INSTANCE: MealsDb? = null

        fun getDatabase(context: Context): MealsDb {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealsDb::class.java,
                    "meals_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}