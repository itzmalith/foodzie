//drive link : https://drive.google.com/drive/folders/1u7_S3GLza34GShlUyj57qIqOzAke0YlE?usp=sharing

package com.example.mealapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // navigate from get started page

        val mainmenue = findViewById(R.id.button2) as Button
        mainmenue.setOnClickListener(){


            val Intent  = Intent(this,OpenScreen::class.java)
            startActivity(Intent)
        }




    }

}