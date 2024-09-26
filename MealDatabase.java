package com.example.foodfinal.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodfinal.model.PlanedMeal;
import com.example.foodfinal.model.Meal;

@Database(entities = {Meal.class, PlanedMeal.class},version = 1)
public abstract class MealDatabase extends RoomDatabase {
    private static MealDatabase instance = null;
    public abstract MealDAO getMealDAO();
    public abstract PlanedMealDAO getPlanedMealDAO();
    public static synchronized MealDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class, "mealDb")
                    .build();
        }
        return instance;
    }
}
