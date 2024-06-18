package com.example.mealapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.data.Meal
import com.example.mealapp.data.MealsDao
import com.example.mealapp.data.MealsDb
import com.example.mealapp.data.MealsRepo

import com.google.android.material.textfield.TextInputEditText

class SearchSavedMeal : AppCompatActivity() {
    private lateinit var mealsDao: MealsDao
    private lateinit var mealsDatabse: MealsDb
    private lateinit var MealsRepo: MealsRepo
    private var mealsList: ArrayList<Meal> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_saved_meal)

        //instance of the room db
        mealsDatabse = MealsDb.getDatabase(this)

        //reference of DAO methods to interact with db
        mealsDao = mealsDatabse.mealDao()

        //repo object which acts as a mediator between the viewmodel and DAO
        MealsRepo = MealsRepo(mealsDao)

        //retrieve the id's from the layouts
        val searchMeal = findViewById<Button>(R.id.retrieveMeals2)
        val mealInput = findViewById<EditText>(R.id.searchviews3)
        val recyclerView: RecyclerView = findViewById(R.id.mealRecyclerView2)

        //action to perform on search meals button
        searchMeal.setOnClickListener {
            //clear the mealsList arraylist
            mealsList.clear()

            val mealsRepo = MealsRepo(mealsDao)

            mealsRepo.searchMeals(mealInput.text.toString()).observe(this, Observer { meals ->
                mealsList.clear() // clear the mealsList before adding new data
                mealsList.addAll(meals) // add the retrieved meals to the mealsList

                //recycler view to dynamically display the list of json data
                recyclerView?.layoutManager = LinearLayoutManager(this@SearchSavedMeal)
                recyclerView?.adapter = RecyclerViewAdapter(mealsList)
            })
        }

    }

    //save instances to state
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the state of your activity to the outState Bundle
        outState.putParcelableArrayList("mealsList", mealsList)

    }

    //restore instances from state
    override fun onRestoreInstanceState(savedInstanceState: Bundle) { // Here You have to restore count value
        super.onRestoreInstanceState(savedInstanceState)

        mealsList = savedInstanceState.getParcelableArrayList("mealsList") ?: ArrayList()

        //recycler view to dynamically display the list of json data
        val recyclerView: RecyclerView = findViewById(R.id.mealRecyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = RecyclerViewAdapter(mealsList)

    }

}