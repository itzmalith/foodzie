package com.example.mealapp.data

import androidx.lifecycle.LiveData

class MealsRepo(private val MealDao: MealsDao) {

    val readAllMealsData: LiveData<List<Meal>> = MealDao.getMealsByAsc()
    suspend fun addMeal(meal: Meal) {
        MealDao.insertMeal(meal)
    }

    fun searchMeals(searchQuery: String): LiveData<List<Meal>> {
        val query = "%$searchQuery%"
        return MealDao.searchMealsDB(query)
    }

}