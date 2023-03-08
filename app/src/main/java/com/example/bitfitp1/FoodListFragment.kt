package com.example.bitfitp1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


private const val TAG = "FoodListFragment"

class FoodListFragment : Fragment(){

    private val foods = mutableListOf<DisplayFood>()
    private lateinit var listsRv: RecyclerView
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_list, container, false)
        foodAdapter = FoodAdapter(view.context, foods)
        listsRv = view.findViewById(R.id.listsRv)
        listsRv.adapter = foodAdapter
        listsRv.layoutManager = LinearLayoutManager(context)


        return view
    }
    companion object {

        fun newInstance(): FoodListFragment{
            return FoodListFragment()
        }

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            (activity?.application as FoodApplication).db.FoodDao().getAll().collect{ databaseList ->
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
    }




}