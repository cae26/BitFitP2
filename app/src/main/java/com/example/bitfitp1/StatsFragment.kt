package com.example.bitfitp1


import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch



private const val TAG = "FoodListFragment"

class StatsFragment: Fragment() {


    private val foods = mutableListOf<DisplayFood>()
    private lateinit var foodsRelativeView: RelativeLayout
    private lateinit var avgCalories: TextView
    private lateinit var avgCalNum: TextView
    private lateinit var minCal: TextView
    private lateinit var maxCal: TextView
    private lateinit var foodAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        val layoutManager = LinearLayoutManager(context)
        foodsRelativeView = view.findViewById(R.id.relView)
        avgCalories = view.findViewById(R.id.avgCal)
        avgCalNum = view.findViewById(R.id.avgCalNum)
        minCal =  view.findViewById(R.id.minCal)
        maxCal =  view.findViewById(R.id.maxCal)


        return view
    }





    private fun fetchAvg(){
        lifecycleScope.launch(IO) {
            avgCalories.text = (activity?.application as FoodApplication).db.FoodDao().avg().toString()
            minCal.text=(activity?.application as FoodApplication).db.FoodDao().min().toString()
            maxCal.text=(activity?.application as FoodApplication).db.FoodDao().max().toString()
            }
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        fetchAvg()
    }
    companion object {

        fun newInstance(): StatsFragment {
            return StatsFragment()
        }
    }


}