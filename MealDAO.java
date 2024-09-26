package com.example.foodfinal.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodfinal.model.Meal;

import java.util.List;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getMeals();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal movie);

    @Delete
    void deleteMeal(Meal product);


}
