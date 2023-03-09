package com.example.bitfitp1
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
//import com.example.bitfitp1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val foods = mutableListOf<DisplayFood>()
    //private lateinit var foodsRecyclerView: RecyclerView
    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: LayoutInflater



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //binding = ActivityMainBinding.inflate(layoutInflater)
       // val view = binding.root
       //setContentView(view)
        setContentView(R.layout.activity_main)


        //foodsRecyclerView = findViewById(R.id.recyclerView)

        //val foodAdapter = FoodAdapter(this,foods)
        //foodsRecyclerView.adapter = foodAdapter


//        lifecycleScope.launch {
//            (application as FoodApplication).db.FoodDao().getAll().collect{ databaseList ->
//                databaseList.map {entity ->
//                    DisplayFood(
//                        entity.foodName,
//                        entity.numberOfCalories
//
//                    )
//                }.also { mappedList ->
////                    foods.clear()
//                    foods.addAll(mappedList)
//                    foodAdapter.notifyDataSetChanged()
//                }
//            }
//
//        }
//        foodsRecyclerView.layoutManager = LinearLayoutManager(this).also {
//            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
//            foodsRecyclerView.addItemDecoration(dividerItemDecoration)
//        }
        val addNewFButton = findViewById<Button>(R.id.button2)

        //val addFoodFragment: Fragment = FoodListFragment()
        //val foodListFragment: Fragment = StatsFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_stats -> fragment =  StatsFragment()
                R.id.nav_log -> fragment =FoodListFragment()
            }
            replaceFragment(fragment)
            true
        }


        bottomNavigationView.selectedItemId = R.id.nav_log
        addNewFButton.setOnClickListener{
            val intent = Intent(this@MainActivity,DetailsActivity::class.java)


            startActivity(intent)

        }

    }
    private fun replaceFragment(foodListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.food_frame_layout, foodListFragment)
        fragmentTransaction.commit()
    }
}