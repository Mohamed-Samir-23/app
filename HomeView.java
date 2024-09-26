package com.example.foodfinal.home.view;

import com.example.foodfinal.model.Meal;

import java.util.List;

public interface HomeView {
    void showMeals(List<Meal> products);
    void showErr(String error);
}
