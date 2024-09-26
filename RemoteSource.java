package com.example.foodfinal.network;

import android.database.Observable;

import com.example.foodfinal.model.CountryResponse;
import com.example.foodfinal.model.CategoryResponse;
import com.example.foodfinal.model.IngredientResponse;
import com.example.foodfinal.model.MealResponse;


public interface RemoteSource {

    Observable<MealResponse> getMealByName(String name);

    Observable<MealResponse> getMealByFirstChar(String firstChar);

    Observable<MealResponse> getMealById(String id);

    Observable<MealResponse> getRandomMeal();

    Observable<CategoryResponse> getAllCategories();

    Observable<CountryResponse> getAllCountries();

    Observable<IngredientResponse> getAllIngredients();

    Observable<MealResponse> getMealsByIngredient(String ingredient);

    Observable<MealResponse> getMealsByCategory(String category);

    Observable<MealResponse> getMealsByCountry(String country);

    Observable<MealResponse> getRandomMeals();
}