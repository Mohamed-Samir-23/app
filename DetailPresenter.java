package com.example.foodfinal.details.presenter;

import com.example.foodfinal.details.view.DetailsView;
import com.example.foodfinal.model.Meal;
import com.example.foodfinal.network.APIClient;
import com.example.foodfinal.network.NetworkCallBack;

import java.util.List;

public class DetailPresenter implements DetailPresenterInterface , NetworkCallBack<Meal> {
    private final DetailsView view;
    private final APIClient client;
    public DetailPresenter(DetailsView view , APIClient client) {
        this.view = view;
        this.client = client;
    }


    @Override
    public void loadMealsInDetails(String id) {

       client.getMealById(id , this);
    }



    @Override
    public void addToFavourite(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> response) {
        view.showDetails(response.get(0));
    }

    @Override
    public void onFailure(String error) {
        view.showErr(error);
    }
}
