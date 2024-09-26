package com.example.foodfinal.details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodfinal.R;
import com.example.foodfinal.model.IngredientModel;

import java.util.ArrayList;

public class IngrediantAdaptor extends RecyclerView.Adapter<IngrediantAdaptor.IngrediantViewHolder>   {

    private ArrayList<IngredientModel> ingredientList = new ArrayList<>();
    private Context context;

    public IngrediantAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public IngrediantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ingre_item, parent, false);
        return new IngrediantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngrediantViewHolder holder, int position) {

        holder.txtIngrediant.setText(ingredientList.get(position).getIngredientName());
        holder.txtMeasure.setText(ingredientList.get(position).getIngredientMeasure());
        Glide.with(context).load(ingredientList.get(position).getIngredientThumb()).apply(new RequestOptions().override(500,500).placeholder(R.drawable.ic_launcher_foreground)).into(holder.ingImg);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public void setList(ArrayList<IngredientModel> myList) {
        this.ingredientList = myList;
        notifyDataSetChanged();
    }

    public class IngrediantViewHolder extends RecyclerView.ViewHolder {
        ImageView ingImg;
        TextView txtMeasure , txtIngrediant;

        public IngrediantViewHolder(@NonNull View itemView) {
            super(itemView);
            ingImg = itemView.findViewById(R.id.ingredientImg);
            txtMeasure = itemView.findViewById(R.id.txtMeasure);
            txtIngrediant = itemView.findViewById(R.id.txtIngredient);
        }
    }

}
