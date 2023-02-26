package com.example.bitfitp1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

//import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val recordFoodButton = findViewById<Button>(R.id.button)
        val foodName = findViewById<EditText>(R.id.foodEditText)
        val calories = findViewById<EditText>(R.id.caloriesEditText)

        recordFoodButton.setOnClickListener {

           // val caloriesStringTExtView = findViewById<TextView>(R.id.caloriesEditText).text.toString()
            val food = foodName.text.toString()
            val cal = calories.text.toString()

            lifecycleScope.launch(IO) {
                (application as FoodApplication).db.FoodDao().insert(
                    FoodEntity(food, cal)

                )
            }


            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}