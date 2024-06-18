package com.example.mealapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast

class Categorymeal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorymeal)

        for (i in 1..14) {
            val testbtn = findViewById<LinearLayout>(resources.getIdentifier("c$i", "id", packageName))
            testbtn.setOnClickListener {
                val message = "Under development ,stay tuned!"
                showToast(this, message)
            }
        }
    }
    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


}