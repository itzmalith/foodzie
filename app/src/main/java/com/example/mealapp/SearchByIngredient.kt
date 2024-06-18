package com.example.mealapp
import androidx.appcompat.widget.SearchView
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.data.Meal
import com.example.mealapp.data.MealsDao
import com.example.mealapp.data.MealsDb
import com.example.mealapp.data.MealsRepo
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class SearchByIngredient : AppCompatActivity() {

    //DB instances and configs
    private lateinit var mealsDao: MealsDao
    private lateinit var mealsDatabse: MealsDb
    private lateinit var MealsRepo: MealsRepo
    private var mealsList: ArrayList<Meal> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_by_ingredient)

        //instance of the room db
        mealsDatabse = MealsDb.getDatabase(this)

        //reference of DAO methods to interact with db
        mealsDao = mealsDatabse.mealDao()

        //repo object which acts as a mediator between the viewmodel and DAO
        MealsRepo = MealsRepo(mealsDao)

        //retrieve the id's from the layouts
        val saveMeals = findViewById(R.id.buttonz) as Button
        val search = findViewById(R.id.retrieveMeals) as Button

        val ingredientInput = findViewById<EditText>(R.id.searchviews)
        val recyclerView: RecyclerView = findViewById(R.id.mealRecyclerView)


        //retrieved array
        var mealsJS = JSONArray()

//        action to perform on retrive meals button
        search.setOnClickListener {
            //clear the mealsList arraylist
            mealsList.clear()

            val ingredient = ingredientInput.text.toString()

            lifecycleScope.launch {
                val response = httpGetMeal(ingredient)
                try {
                    val mealData = JSONObject(response)
                    mealsJS = mealData.getJSONArray("meals")

                    //iterate over the array to get individual objects and assign it to an array
                    for (i in 0 until mealsJS.length()) {
                        val singlemeal = mealsJS.getJSONObject(i)
                        val meal = createMealFromJSON(singlemeal)
                        mealsList.add(meal)
                    }

                    //recycler view to dynamically display the list of json data
                    recyclerView?.layoutManager = LinearLayoutManager(this@SearchByIngredient)
                    recyclerView?.adapter = RecyclerViewAdapter(mealsList)

                } catch (e: JSONException) {
                    // handle error
                    Log.d("Error : ", "Error parsing JSON response: ${e.message}")

                }
            }
        }



//        action to perform on save meals button
        saveMeals.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                //iterate over the array to get individual objects and save it to db
                for (i in 0 until mealsJS.length()) {
                    val singlemeal = mealsJS.getJSONObject(i)
                    val meal = createMealFromJSON(singlemeal)
                    MealsRepo.addMeal(meal)

                }
            }
        }
    }

    //map meals json object to entity
    fun createMealFromJSON(singlemeal: JSONObject): Meal {
        return Meal(
            singlemeal.getString("idMeal"),
            singlemeal.getString("strMeal"),
            singlemeal.getString("strDrinkAlternate"),
            singlemeal.getString("strCategory"),
            singlemeal.getString("strArea"),
            singlemeal.getString("strInstructions"),
            singlemeal.getString("strMealThumb"),
            singlemeal.getString("strTags"),
            singlemeal.getString("strYoutube"),
            singlemeal.getString("strIngredient1"),
            singlemeal.getString("strIngredient2"),
            singlemeal.getString("strIngredient3"),
            singlemeal.getString("strIngredient4"),
            singlemeal.getString("strIngredient5"),
            singlemeal.getString("strIngredient6"),
            singlemeal.getString("strIngredient7"),
            singlemeal.getString("strIngredient8"),
            singlemeal.getString("strIngredient9"),
            singlemeal.getString("strIngredient10"),
            singlemeal.getString("strMeasure1"),
            singlemeal.getString("strMeasure2"),
            singlemeal.getString("strMeasure3"),
            singlemeal.getString("strMeasure4"),
            singlemeal.getString("strMeasure5"),
            singlemeal.getString("strMeasure6"),
            singlemeal.getString("strMeasure7"),
            singlemeal.getString("strMeasure8"),
            singlemeal.getString("strMeasure9"),
            singlemeal.getString("strMeasure10"),
        )
    }

    //http GET request to retrieve data
    private suspend fun httpGetMeal(ingredient: String?): String? {

        return withContext(Dispatchers.IO) {

            val inputStream: InputStream
            var result: String?

            // create URL
            val url = URL("https://www.themealdb.com/api/json/v1/1/search.php?s=$ingredient")

            // create HttpURLConnection
            val conn = url.openConnection() as HttpURLConnection

            // make GET request to the given URL
            conn.connect()

            // receive response as inputStream
            inputStream = conn.inputStream

            // convert inputstream to string
            result = inputStream.bufferedReader().use { it.readText() }

            result
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
        val recyclerView: RecyclerView = findViewById(R.id.mealRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@SearchByIngredient)
        recyclerView?.adapter = RecyclerViewAdapter(mealsList)

    }

}