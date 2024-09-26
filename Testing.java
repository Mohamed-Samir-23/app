package com.example.foodfinal.network;

import android.util.Log;

import com.example.foodfinal.model.Category;
import com.example.foodfinal.model.Country;
import com.example.foodfinal.model.Ingredient;
import com.example.foodfinal.model.Meal;

import java.util.List;

public class Testing {

    private static final String TAG = "Testing";
    public void testCategories() {
        APIClient.getInstance().getCategoriesList(new NetworkCallBack<Category>() {
            @Override
            public void onSuccess(List<Category> categories) {
                for (Category category : categories) {
                    Log.i(TAG, "Category: " + category.getStrCategory());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to fetch categories: " + errorMsg);
            }
        });
    }

    public void testIngredients() {
        APIClient.getInstance().getIngredientsList(new NetworkCallBack<Ingredient>() {
            @Override
            public void onSuccess(List<Ingredient> ingredients) {
                for (Ingredient ingredient : ingredients) {
                    Log.i(TAG, "Ingredient: " + ingredient.getStrIngredient());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to fetch ingredients: " + errorMsg);
            }
        });
    }

    public void testCountries() {
        APIClient.getInstance().getCountriesList(new NetworkCallBack<Country>() {
            @Override
            public void onSuccess(List<Country> countries) {
                for (Country country : countries) {
                    Log.i(TAG, "Country: " + country.getStrArea());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to fetch countries: " + errorMsg);
            }
        });
    }

    public void testRandomMeal() {
        APIClient.getInstance().getMealRandom(new NetworkCallBack<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                for (Meal meal : meals) {
                    Log.i(TAG, "Meal: " + meal.getIdMeal());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to fetch meals: " + errorMsg);
            }
        });
    }

    public void testMealsByCategory() {
        APIClient.getInstance().getMealByCategory("Seafood", new NetworkCallBack<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    for (Meal meal : meals) {
                        Log.i(TAG, "Meal by Category: " + meal.getStrMeal());
                    }
                } else {
                    Log.i(TAG, "No meals found for category: " + "Seafood");
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to get meals by category: " + errorMsg);
            }
        });
    }

    public void testMealsByIngredient() {
        APIClient.getInstance().getMealByIngredient("chicken_breast", new NetworkCallBack<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    for (Meal meal : meals) {
                        Log.i(TAG, "Meal by Ingredient: " + meal.getStrMeal());
                    }
                } else {
                    Log.i(TAG, "No meals found for ingredient: " + "chicken_breast");
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to get meals by ingredient: " + errorMsg);
            }
        });
    }

    public void testMealsByArea() {
        APIClient.getInstance().getMealByArea("Canadian", new NetworkCallBack<Meal>() {
            @Override
            public void onSuccess(List<Meal> meals) {
                if (meals != null && !meals.isEmpty()) {
                    for (Meal meal : meals) {
                        Log.i(TAG, "Meal by Area: " + meal.getStrMeal());
                    }
                } else {
                    Log.i(TAG, "No meals found for area: " + "Canadian");
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e(TAG, "Failed to get meals by area: " + errorMsg);
            }
        });
    }
}
