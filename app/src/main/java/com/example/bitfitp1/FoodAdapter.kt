package com.example.bitfitp1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide


const val FOOD_EXTRA = "FOOD_EXTRA"
private const val TAG = "FoodAdapter"

class FoodAdapter(private val context: Context, private val foods: List<DisplayFood>) :
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_food,parent,false)
        return ViewHolder(view)

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val food = foods[position]
        holder.bind(food)
    }

    override fun getItemCount() = foods.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val foodNTextView = itemView.findViewById<TextView>(R.id.fooditemView)
        private val caloriesNTextView = itemView.findViewById<TextView>(R.id.caloriesitemView)
        fun bind(food: DisplayFood) {

            foodNTextView.text = food.foodName
            caloriesNTextView.text = food.numOfCalories

        }



    }



    }