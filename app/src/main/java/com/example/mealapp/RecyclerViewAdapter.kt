package com.example.mealapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealapp.data.Meal

import java.util.concurrent.Executors


class RecyclerViewAdapter(private val mealsList: ArrayList<Meal>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imageView)
        val name = itemView.findViewById<TextView>(R.id.tvName)
        val email = itemView.findViewById<ImageView>(R.id.tvEmail)
        val boton = itemView.findViewById<ImageView>(R.id.seemoree)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.meal_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealsList[position]

        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()

        // when the executor parses the URL and receives the image, handler will load it
        val handler = Handler(Looper.getMainLooper())

        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(meal.MealThumb).openStream()
                image = BitmapFactory.decodeStream(`in`)

                // Only for making changes in UI
                handler.post {
                    holder.img.setImageBitmap(image)
                }
            }

            // If the URL does not point to image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
        val stringBuilder = StringBuilder()



//        fun findingredients() {
//            val ingredients = arrayOf(meal.Ingredient1, meal.Ingredient2,meal.Ingredient3,meal.Ingredient4,meal.Ingredient5,meal.Ingredient6,meal.Ingredient7,meal.Ingredient8,meal.Ingredient9,meal.Ingredient10)

//        }

        stringBuilder.append(meal.Meal)


        holder.name.text = stringBuilder


        //making link clickable

        holder.email.setOnClickListener {
            val url = meal.Youtube
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            holder.email.context.startActivity(intent)
        }

        //see more card view


        holder.boton.setOnClickListener(){
            val dialogView = LayoutInflater.from(context).inflate(R.layout.mealfullviewitem, null)

            dialogView.findViewById<ImageView>(R.id.idz1).setImageBitmap(image)

            //extracting ids and assigning values accordingly

            dialogView.findViewById<TextView>(R.id.idz2).text = meal.Meal
            dialogView.findViewById<TextView>(R.id.idz3).text = meal.Category
            dialogView.findViewById<TextView>(R.id.mealid).text = meal.id
            dialogView.findViewById<TextView>(R.id.alternate).text = meal.DrinkAlternate
            dialogView.findViewById<TextView>(R.id.I1).text = meal.Ingredient1
            dialogView.findViewById<TextView>(R.id.I2).text = meal.Ingredient2
            dialogView.findViewById<TextView>(R.id.I3).text = meal.Ingredient3
            dialogView.findViewById<TextView>(R.id.I4).text = meal.Ingredient4
            dialogView.findViewById<TextView>(R.id.I5).text = meal.Ingredient5
            dialogView.findViewById<TextView>(R.id.I6).text = meal.Ingredient6
            dialogView.findViewById<TextView>(R.id.I7).text = meal.Ingredient7
            dialogView.findViewById<TextView>(R.id.I8).text = meal.Ingredient8
            dialogView.findViewById<TextView>(R.id.I9).text = meal.Ingredient9
            dialogView.findViewById<TextView>(R.id.I10).text = meal.Ingredient10

            dialogView.findViewById<TextView>(R.id.M1).text = meal.Measure1
            dialogView.findViewById<TextView>(R.id.M2).text = meal.Measure2
            dialogView.findViewById<TextView>(R.id.M3).text = meal.Measure3
            dialogView.findViewById<TextView>(R.id.M4).text = meal.Measure4
            dialogView.findViewById<TextView>(R.id.M5).text = meal.Measure5
            dialogView.findViewById<TextView>(R.id.M6).text = meal.Measure6
            dialogView.findViewById<TextView>(R.id.M7).text = meal.Measure7
            dialogView.findViewById<TextView>(R.id.M8).text = meal.Measure8
            dialogView.findViewById<TextView>(R.id.M9).text = meal.Measure9
            dialogView.findViewById<TextView>(R.id.M10).text = meal.Measure10
            dialogView.findViewById<TextView>(R.id.instruction).text = meal.Instuctions




            val abtDialog = Dialog(context)
            abtDialog.setContentView(dialogView)

            //popup close

            abtDialog.setCancelable(true)
            abtDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            abtDialog.show()

            dialogView.findViewById<ImageView>(R.id.closeBtn)?.setOnClickListener {
                abtDialog.dismiss()
            }


    }
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }
}