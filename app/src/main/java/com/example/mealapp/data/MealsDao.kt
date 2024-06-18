package com.example.mealapp.data
import androidx.room.*


import androidx.lifecycle.LiveData
import androidx.room.*

//methods for performing CRUD
@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal: Meal)

    @Query("SELECT * FROM meals_db ORDER BY Meal ASC ")
    fun getMealsByAsc(): LiveData<List<Meal>>

    @Query("SELECT * FROM meals_db ORDER BY Area ASC ")
    fun getAreaByAsc(): LiveData<List<Meal>>

    @Query("SELECT * FROM meals_db ORDER BY Tags ASC ")
    fun getTagsByAsc(): LiveData<List<Meal>>

    @Query("SELECT * FROM meals_db WHERE Meal LIKE :searchQuery OR Ingredient1 LIKE :searchQuery OR Ingredient2 LIKE :searchQuery OR Ingredient3 LIKE :searchQuery OR Ingredient4 LIKE :searchQuery OR Ingredient5 LIKE :searchQuery OR Ingredient6 LIKE :searchQuery OR Ingredient7 LIKE :searchQuery OR Ingredient8 LIKE :searchQuery OR Ingredient9 LIKE :searchQuery OR Ingredient10 LIKE :searchQuery ORDER BY Tags ASC ")
    fun searchMealsDB(searchQuery: String): LiveData<List<Meal>>

}