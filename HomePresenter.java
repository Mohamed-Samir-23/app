package com.example.foodfinal.home.Presenter;

import com.example.foodfinal.home.view.HomeView;
import com.example.foodfinal.model.Meal;
import com.example.foodfinal.network.APIClient;
import com.example.foodfinal.network.NetworkCallBack;

import java.util.List;

public class HomePresenter implements HomePresenterInterface, NetworkCallBack<Meal> {

    private final HomeView view;
   // private final MealRepository mealRepo;
    private final APIClient client;
    public HomePresenter(HomeView view, APIClient client) {
        this.view = view;
        this.client = client;
    }

    @Override
    public void loadMeals() {
        //APIClient client1 = APIClient.getInstance();
        client.getMealRandom(this);

    }

    @Override
    public void addToFavourite(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> response) {
        view.showMeals(response);
    }

    @Override
    public void onFailure(String error) {
        view.showErr(error);
    }
}
