package com.example.foodfinal.details.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodfinal.R;
import com.example.foodfinal.details.presenter.DetailPresenter;
import com.example.foodfinal.model.IngredientModel;
import com.example.foodfinal.model.Meal;
import com.example.foodfinal.network.APIClient;
import com.example.foodfinal.view.HostedActivity;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DetailsFragment extends Fragment implements DetailsView {

   
    private ImageView mealImg;
    private TextView mealName, mealCountry, mealDesc ;
    private ImageButton toFav;

    private RecyclerView ingRecyclerView;
    private RecyclerView.LayoutManager detaileLayoutManager;
    private DetailPresenter detailPresenter;
    private YouTubePlayerView youTubePlayer;
    private IngrediantAdaptor ingrediantAdaptor;
    private DetailPresenter detailesPresenter;
    private Meal meal ;
    private int mSelectedIndex;
    String[] videoArray;
    String videoString;

    @Override
    public void onStart() {
        super.onStart();
        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealImg = view.findViewById(R.id.detailsImageView);
        mealName = view.findViewById(R.id.detMealName);
        mealCountry= view.findViewById(R.id.detCountryName);
        mealDesc = view.findViewById(R.id.detailsDescriptionOfmeal);
        ingRecyclerView = view.findViewById(R.id.detailsIngredientRecycler);
        youTubePlayer = view.findViewById(R.id.youtubePlayer);
        detaileLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL , false);
        ingRecyclerView.setLayoutManager(detaileLayoutManager);

        ingrediantAdaptor = new IngrediantAdaptor(view.getContext());
        ingRecyclerView.setAdapter(ingrediantAdaptor);

        detailPresenter = new DetailPresenter(this , new APIClient());

        toFav.setOnClickListener(v -> {
            Toast.makeText(view.getContext(), "soon", Toast.LENGTH_SHORT).show();
        });

        if (getArguments() != null) {
            String id = getArguments().getString("id");
            if (id != null) {
                detailPresenter.loadMealsInDetails(id);
            }
        }




    }

    @Override
    public void onResume() {
        super.onResume();
        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void showDetails(Meal meal) {
        Glide.with(getContext()).load(meal.getStrMealThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(mealImg);
        mealName.setText(meal.getStrMeal());
        mealCountry.setText(meal.getStrArea());
        mealDesc.setText(meal.getStrInstructions());

        if (!meal.getStrYoutube().equals("")) {
            videoArray = meal.getStrYoutube().split("=");
            videoString = videoArray[1];
        } else {
            videoString = "";
        }


        youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoString, 0);
                youTubePlayer.pause();
            }
        });

      ArrayList<IngredientModel> ingredientPojos = getIngList(meal);
        ingrediantAdaptor.setList(ingredientPojos);
        ingrediantAdaptor.notifyDataSetChanged();
    }

    private ArrayList<IngredientModel> getIngList(Meal meal) {
        ArrayList<IngredientModel> ingList = new ArrayList<>();
        for (int i =0 ; i <= 20; i++) {
            try {
                String ingredient = (String) meal.getClass().getMethod("getStrIngredient" + i).invoke(meal);
                String measure = (String) meal.getClass().getMethod("getStrMeasure" + i).invoke(meal);
                if (ingredient != null && !ingredient.isEmpty() && measure != null && !measure.isEmpty()) {
                    String imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredient + ".png";
                    ingList.add(new IngredientModel(ingredient, measure, imageUrl));
                }
            } catch (NoSuchMethodException  | InvocationTargetException | IllegalAccessException e )
            {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

            return ingList;
    }

    @Override
    public void showErr(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ((HostedActivity) requireActivity()).bottomNavigationView.setVisibility(View.VISIBLE);
    }
}