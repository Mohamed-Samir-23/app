package com.example.foodfinal.database;

import androidx.lifecycle.LiveData;

import com.example.foodfinal.model.PlanedMeal;

import java.util.List;

public interface PlanedMealRepoInterface {

    LiveData<List<PlanedMeal>> getPlanedMeals();
    void insertMeal(PlanedMeal meal);
    void deletePlanedMeal(PlanedMeal meal);
}
