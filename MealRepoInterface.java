package com.example.foodfinal.database;

import androidx.lifecycle.LiveData;

import com.example.foodfinal.model.Meal;

import java.util.List;

public interface MealRepoInterface {
    LiveData<List<Meal>> getMeals();
    void insertMeal(Meal meal);
    void deleteMeal(Meal meal);
}
