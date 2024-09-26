package com.example.foodfinal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "planMeal_table")
public class PlanedMeal {
    @PrimaryKey
    public int id;

    public String dayOfWeek;
    public String mealId;
}
