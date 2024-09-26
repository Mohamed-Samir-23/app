package com.example.foodfinal.details.view;

import com.example.foodfinal.model.Meal;

public interface DetailsView {

    void showDetails(Meal meal);
    void showErr(String error);

}
