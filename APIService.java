package com.example.foodfinal.network;

import com.example.foodfinal.model.Category;
import com.example.foodfinal.model.Country;
import com.example.foodfinal.model.Ingredient;
import com.example.foodfinal.model.Meal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    // for Category
    @GET("list.php?c=list")
    Call<NetworkResponse<Category>> getCategoriesList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByCategory(@Query("c") String category);


    //for Country
    @GET("list.php?a=list")
    Call<NetworkResponse<Country>> getCountriesList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByArea(@Query("a") String area);


    //for Ingredients
    @GET("list.php?i=list")
    Call<NetworkResponse<Ingredient>> getIngredientsList();
    @GET("filter.php")
    Call<NetworkResponse<Meal>> getMealByIngredient(@Query("i") String ingredient);


    // by Id
    @GET("lookup.php")
    Call<NetworkResponse<Meal>> getMealById(@Query("i") String id);


    // for random
    @GET("random.php")
    Call<NetworkResponse<Meal>> getMealRandom();






}
