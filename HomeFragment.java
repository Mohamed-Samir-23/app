package com.example.foodfinal.home.view;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodfinal.R;
import com.example.foodfinal.database.MealRepository;
import com.example.foodfinal.home.Presenter.HomePresenter;
import com.example.foodfinal.model.Meal;
import com.example.foodfinal.network.APIClient;
import com.example.foodfinal.network.NetworkChecking;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeOnClickListener,HomeView {

    private RecyclerView daily;
    private RecyclerView.LayoutManager dailyLayoutManager;
    private TextView txtappName, txtqoute ,txtdaily;
    // private ImageView logo;
    private APIClient client;
    private MealRepository repo;

    private DailyAdapter dailyAdapter;
    private HomePresenter presenter;
    private List<Meal> dailyMeal;

    private Handler handler;
    private Snackbar snackbar;
    private boolean mealsLoaded = false;
    private static final int CHECK_INTERVAL = 500;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyMeal = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtappName = view.findViewById(R.id.appname);
        txtqoute = view.findViewById(R.id.appquote);
        txtdaily = view.findViewById(R.id.txtInsp);
        //     logo = view.findViewById(R.id.logoImg);


        daily = view.findViewById(R.id.inspRecycler);

         dailyLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL , false);
        daily.setLayoutManager(dailyLayoutManager);

        client = APIClient.getInstance();



        presenter = new HomePresenter(this,new APIClient());
        handler = new Handler(Looper.getMainLooper());
        checkNetwork();



    }

    @Override
    public void onDailyInspireItemClicked(String id) {
        Toast.makeText(getContext(), "Description will be shown", Toast.LENGTH_SHORT).show();
        Bundle args = new Bundle();
        args.putString("id", id);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(R.id.action_HOmeFrag_to_detailFrag, args);
    }

    @Override
    public void showMeals(List<Meal> meals) {
        Log.i(TAG, "showProducts: ");
        for(Meal meal: meals){
            Log.i(TAG, "ID: " + meal.getIdMeal());
        }
        dailyAdapter = new DailyAdapter(meals ,getContext() , this) ;
        daily.setAdapter(dailyAdapter);


    }

    @Override
    public void showErr(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    private void checkNetwork() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (NetworkChecking.isNetworkAvailable(getContext())) {
                    if (snackbar != null && snackbar.isShown()) {
                        snackbar.dismiss();
                    }
                    if (!mealsLoaded) {
                        presenter.loadMeals();
                        mealsLoaded = true;
                    }
                } else {
                    if (snackbar == null || !snackbar.isShown()) {

                            snackbar = Snackbar.make(getView().findViewById(android.R.id.content), "No network connection available", Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Settings", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                                        }
                                    });
                            snackbar.show();

                    }
                }
                handler.postDelayed(this, CHECK_INTERVAL);
            }
        }, CHECK_INTERVAL);
    }
}