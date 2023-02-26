package com.example.bitfitp1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")

data class FoodEntity(
    //@PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "foodName") val foodName: String?,
    @ColumnInfo(name = "numberOfCalories") val numberOfCalories:String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
    //@ColumnInfo(name = "byline") val byline: String?,
    //@ColumnInfo(name = "mediaImageUrl") val mediaImageUrl: String?

)