package com.example.bitfitp1
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfitp1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val foods = mutableListOf<DisplayFood>()
    private lateinit var foodsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        foodsRecyclerView = findViewById(R.id.recyclerView)

        val foodAdapter = FoodAdapter(this,foods)
        foodsRecyclerView.adapter = foodAdapter


        lifecycleScope.launch {
            (application as FoodApplication).db.FoodDao().getAll().collect{ databaseList ->
                databaseList.map {entity ->
                    DisplayFood(
                        entity.foodName,
                        entity.numberOfCalories

                    )
                }.also { mappedList ->
//                    foods.clear()
                    foods.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }

        }
        foodsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        val addNewFButton = findViewById<Button>(R.id.button2)
        addNewFButton.setOnClickListener{
            val intent = Intent(this@MainActivity,DetailsActivity::class.java)


            startActivity(intent)

        }

    }
}