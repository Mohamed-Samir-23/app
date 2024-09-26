package com.example.foodfinal.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodfinal.model.PlanedMeal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlanedMealRepository implements PlanedMealRepoInterface{

    private final  PlanedMealDAO planedMealDAO;
    private final ExecutorService executorService;

    public PlanedMealRepository(Context context) {
        MealDatabase db = MealDatabase.getInstance(context);
        planedMealDAO = db.getPlanedMealDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public LiveData<List<PlanedMeal>> getPlanedMeals() {
        return planedMealDAO.getPlanedMeals();
    }

    @Override
    public void insertMeal(PlanedMeal meal) {
        executorService.execute(() -> planedMealDAO.insert(meal));
    }

    @Override
    public void deletePlanedMeal(PlanedMeal meal) {
        executorService.execute(() -> planedMealDAO.deleteMealPlan(meal));
    }

}
