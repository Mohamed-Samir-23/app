package com.example.foodfinal.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodfinal.model.Meal;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MealRepository implements MealRepoInterface{

    private static MealRepository instance = null;
    private MealDAO mealDAO;
    private ExecutorService executorService;

    public MealRepository(Context context){
        mealDAO = MealDatabase.getInstance(context).getMealDAO();

        executorService = Executors.newSingleThreadExecutor();
    }


    @Override
    public LiveData<List<Meal>> getMeals() {
        return mealDAO.getMeals();
    }

    @Override
    public void insertMeal(Meal meal) {
        executorService.execute(() -> mealDAO.insertMeal(meal));
    }

    @Override
    public void deleteMeal(Meal meal) {
        executorService.execute(() -> mealDAO.deleteMeal(meal));
    }
}
