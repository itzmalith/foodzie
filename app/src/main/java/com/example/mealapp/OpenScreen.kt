package com.example.mealapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.mealapp.data.Meal
import com.example.mealapp.data.MealsDao
import com.example.mealapp.data.MealsDb
import com.example.mealapp.data.MealsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class OpenScreen : AppCompatActivity() {
    private lateinit var mealsDao: MealsDao
    private lateinit var addedMealsDb: MealsDb
    private lateinit var MealsRepo: MealsRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_screen)

        //instance of the room db
        addedMealsDb = MealsDb.getDatabase(this)

        //reference of DAO methods to interact with db
        mealsDao = addedMealsDb.mealDao()

        //repo object which acts as a mediator between the viewmodel and DAO
        MealsRepo = MealsRepo(mealsDao)

        val btn = findViewById(R.id.button22) as Button
        btn.setOnClickListener(){
            val mealData = JSONObject(
                """{
                            "Meal": "Sweet and Sour Pork",
                            "DrinkAlternate": null,
                            "Category": "Pork",
                            "Area": "Chinese",
                            "Instructions": "Preparation\r\n1. Crack the egg into a bowl. Separate the egg white and yolk.\r\n\r\nSweet and Sour Pork\r\n2. Slice the porktenderloin into ips.\r\n\r\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and anegg white.\r\n\r\n4. Marinade the pork ips for about 20 minutes.\r\n\r\n5. Put the remaining starch in a bowl. Add some water and vinegar tomake a starchy sauce.\r\n\r\nSweet and Sour Pork\r\nCooking Inuctions\r\n1. Pour the cooking oil into a wok and heat to 190\u00b0C(375\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\r\n\r\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\r\n\r\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\r\n\r\n4. Pour in thestarchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\r\n\r\n5. Serve on a plate and add somecoriander for decoration.",
                            "MealThumb": "https:\/\/www.themealdb.com\/images\/media\/meals\/1529442316.jpg",
                            "Tags": "Sweet",
                            "Youtube": "https:\/\/www.youtube.com\/watch?v=mdaBIhgEAMo",
                            "Ingredient1": "Pork",
                            "Ingredient2": "Egg",
                            "Ingredient3": "Water",
                            "Ingredient4": "Salt",
                            "Ingredient5": "Sugar",
                            "Ingredient6": "Soy Sauce",
                            "Ingredient7": "Starch",
                            "Ingredient8": "Tomato Puree",
                            "Ingredient9": "Vinegar",
                            "Ingredient10": "Coriander",
                            "Measure1": "200g",
                            "Measure2": "1",
                            "Measure3": "Dash",
                            "Measure4": "1\/2 tsp",
                            "Measure5": "1 tsp ",
                            "Measure6": "10g",
                            "Measure7": "10g",
                            "Measure8": "30g",
                            "Measure9": "10g",
                            "Measure10": "Dash"
                }""")

            val mealData2 = JSONObject(
                """{
                       "Meal":"Chicken Marengo","DrinkAlternate":null,"Category":"Chicken","Area":"French","Instructions":"Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legsand cook briefly on each side to colour them a little.\r\nPour in the passata, crumble in the stock cube and stir in the olives. Season withblack pepper \u2013 you shouldn\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and servewith pasta and a salad, or mash and green veg, if you like.","MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/qpxvuq1511798906.jpg","Tags":null,"Youtube":"https:\/\/www.youtube.com\/watch?v=U33HYUr-0Fw","Ingredient1":"Olive Oil","Ingredient2":"Mushrooms","Ingredient3":"Chicken Legs","Ingredient4":"Passata","Ingredient5":"Chicken Stock Cube","Ingredient6":"Black Olives","Ingredient7":"Parsley", "Ingredient8":"","Ingredient9":"","Ingredient10":"",     "Measure1":"1 tbs","Measure2":"300g","Measure3":"4","Measure4":"500g","Measure5":"1","Measure6":"100g ","Measure7":"Chopped","Measure8":"","Measure9":"","Measure10":""
                      
                }""")

            val mealData3 = JSONObject(
                """{
                      "Meal":"Leblebi Soup","DrinkAlternate":null,"Category":"Vegetarian","Area":"Tunisian","Instructions":"Heat the oil in a large pot. Add the onion and cook until translucent.\r\nDrain the soaked chickpeas and add them to the pottogether with the vegetable stock. Bring to the boil, then reduce the heat and cover. Simmer for 30 minutes.\r\nIn the meantime toast thecumin in a small ungreased frying pan, then grind them in a mortar. Add the garlic and salt and pound to a fine paste.\r\nAdd the paste andthe harissa to the soup and simmer until the chickpeas are tender, about 30 minutes.\r\nSeason to taste with salt, pepper and lemon juice andserve hot.","MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/x2fw9e1560460636.jpg","Tags":"Soup","Youtube":"https:\/\/www.youtube.com\/watch?v=BgRifcCwinY","Ingredient1":"Olive Oil","Ingredient2":"Onion","Ingredient3":"Chickpeas","Ingredient4":"Vegetable Stock","Ingredient5":"Cumin","Ingredient6":"Garlic","Ingredient7":"Salt","Ingredient8":"Harissa Spice","Ingredient9":"Pepper","Ingredient10":"Lime","Measure1":"2 tbs","Measure2":"1 medium finely diced","Measure3":"250g","Measure4":"1.5L","Measure5":"1 tsp ","Measure6":"5 cloves","Measure7":"1\/2 tsp","Measure8":"1 tsp ","Measure9":"Pinch","Measure10":"1\/2 "
                      
                }""")

            val mealData4 = JSONObject(
                """{
                     

                            "Meal":"Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
                            "DrinkAlternate":null,
                            "Category":"Beef",
                            "Area":"Vietnamese",
                            "Instructions":"Add'l ingredients: mayonnaise, siracha\r\n\r\n1\r\n\r\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\r\n\r\n2\r\n\r\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\r\n\r\n3\r\n\r\nIn a medium bowl, combine cucumber, juice from half the lime, \u00bc tsp sugar (\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\u2019d like. Season with salt and pepper.\r\n\r\n4\r\n\r\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\r\n\r\n5\r\n\r\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.",
                            "MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/z0ageb1583189517.jpg",
                            "Tags":null,
                            "Youtube":"",
                            "Ingredient1":"Rice",
                            "Ingredient2":"Onion",
                            "Ingredient3":"Lime",
                            "Ingredient4":"Garlic Clove",
                            "Ingredient5":"Cucumber",
                            "Ingredient6":"Carrots",
                            "Ingredient7":"Ground Beef",
                            "Ingredient8":"Soy Sauce",
                            "Ingredient9":"",
                            "Ingredient10":"",
                            "Measure1":"White",
                            "Measure2":"1",
                            "Measure3":"1",
                            "Measure4":"3",
                            "Measure5":"1",
                            "Measure6":"3 oz ",
                            "Measure7":"1 lb",
                            "Measure8": "30g",
                            "Measure9": "10g",
                            "Measure10": "Dash"
                      
                }""")



            val meal = Meal(
                "0", // Auto-generated ID
                mealData.getString("Meal"), mealData.getString("DrinkAlternate"), mealData.getString("Category"),
                mealData.getString("Area"), mealData.getString("Instructions"), mealData.getString("MealThumb"),
                mealData.getString("Tags"), mealData.getString("Youtube"), mealData.getString("Ingredient1"),
                mealData.getString("Ingredient2"), mealData.getString("Ingredient3"), mealData.getString("Ingredient4"),
                mealData.getString("Ingredient5"), mealData.getString("Ingredient6"), mealData.getString("Ingredient7"),
                mealData.getString("Ingredient8"), mealData.getString("Ingredient9"), mealData.getString("Ingredient10"),
                mealData.getString("Measure1"), mealData.getString("Measure2"), mealData.getString("Measure3"),
                mealData.getString("Measure4"), mealData.getString("Measure5"), mealData.getString("Measure6"),
                mealData.getString("Measure7"), mealData.getString("Measure8"), mealData.getString("Measure9"), mealData.getString("Measure10"),
            )

            val meal2 = Meal(
                "1", // Auto-generated ID
                mealData2.getString("Meal"), mealData2.getString("DrinkAlternate"), mealData2.getString("Category"),
                mealData2.getString("Area"), mealData2.getString("Instructions"), mealData2.getString("MealThumb"),
                mealData2.getString("Tags"), mealData2.getString("Youtube"), mealData2.getString("Ingredient1"),
                mealData2.getString("Ingredient2"), mealData2.getString("Ingredient3"), mealData2.getString("Ingredient4"),
                mealData2.getString("Ingredient5"), mealData2.getString("Ingredient6"), mealData2.getString("Ingredient7"),
                mealData2.getString("Ingredient8"), mealData2.getString("Ingredient9"), mealData2.getString("Ingredient10"),
                mealData2.getString("Measure1"), mealData2.getString("Measure2"), mealData2.getString("Measure3"),
                mealData2.getString("Measure4"), mealData2.getString("Measure5"), mealData2.getString("Measure6"),
                mealData2.getString("Measure7"), mealData2.getString("Measure8"), mealData2.getString("Measure9"), mealData2.getString("Measure10"),
            )

            val meal3 = Meal(
                "2", // Auto-generated ID
                mealData3.getString("Meal"), mealData3.getString("DrinkAlternate"), mealData3.getString("Category"),
                mealData3.getString("Area"), mealData3.getString("Instructions"), mealData3.getString("MealThumb"),
                mealData3.getString("Tags"), mealData3.getString("Youtube"), mealData3.getString("Ingredient1"),
                mealData3.getString("Ingredient2"), mealData3.getString("Ingredient3"), mealData3.getString("Ingredient4"),
                mealData3.getString("Ingredient5"), mealData3.getString("Ingredient6"), mealData3.getString("Ingredient7"),
                mealData3.getString("Ingredient8"), mealData3.getString("Ingredient9"), mealData3.getString("Ingredient10"),
                mealData3.getString("Measure1"), mealData3.getString("Measure2"), mealData3.getString("Measure3"),
                mealData3.getString("Measure4"), mealData3.getString("Measure5"), mealData3.getString("Measure6"),
                mealData3.getString("Measure7"), mealData3.getString("Measure8"), mealData3.getString("Measure9"), mealData3.getString("Measure10"),
            )
            val meal4 = Meal(
                "3", // Auto-generated ID
                mealData4.getString("Meal"), mealData4.getString("DrinkAlternate"), mealData4.getString("Category"),
                mealData4.getString("Area"), mealData4.getString("Instructions"), mealData4.getString("MealThumb"),
                mealData4.getString("Tags"), mealData4.getString("Youtube"), mealData4.getString("Ingredient1"),
                mealData4.getString("Ingredient2"), mealData4.getString("Ingredient3"), mealData4.getString("Ingredient4"),
                mealData4.getString("Ingredient5"), mealData4.getString("Ingredient6"), mealData4.getString("Ingredient7"),
                mealData4.getString("Ingredient8"), mealData4.getString("Ingredient9"), mealData4.getString("Ingredient10"),
                mealData4.getString("Measure1"), mealData4.getString("Measure2"), mealData4.getString("Measure3"),
                mealData4.getString("Measure4"), mealData4.getString("Measure5"), mealData4.getString("Measure6"),
                mealData4.getString("Measure7"), mealData4.getString("Measure8"), mealData4.getString("Measure9"), mealData4.getString("Measure10"),
            )

            CoroutineScope(Dispatchers.IO).launch {
                MealsRepo.addMeal(meal)
                MealsRepo.addMeal(meal2)
                MealsRepo.addMeal(meal3)
                MealsRepo.addMeal(meal4)
            }

            val Intent  = Intent(this,Categorymeal::class.java)
            startActivity(Intent)
        }

        val btn2 = findViewById(R.id.button3) as Button
        btn2.setOnClickListener(){
            val Intent2  = Intent(this,SearchByIngredient::class.java)
            startActivity(Intent2)
        }

        val btn3 = findViewById(R.id.button4) as Button
        btn3.setOnClickListener(){
            val Intent3  = Intent(this,Searchmeals::class.java)
            startActivity(Intent3)
        }
        val btn4 = findViewById(R.id.button5) as Button
        btn4.setOnClickListener(){
            val Intent4  = Intent(this,SearchSavedMeal::class.java)
            startActivity(Intent4)
        }

    }

}