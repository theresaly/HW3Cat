package com.example.hw3cat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<SearchAdapter.CatViewHolder> {

    Context context;
    ArrayList<Cat> favourites;

    public FavouriteAdapter(ArrayList<Cat> favourites) {
        this.favourites = favourites;
    }

    @Override
    public SearchAdapter.CatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat, parent, false);
        this.context = parent.getContext();
        SearchAdapter.CatViewHolder catViewHolder = new SearchAdapter.CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.CatViewHolder holder, final int position){
        final Cat catAtPosition = favourites.get(position);
        //System.out.println("Favourite Cat List: "  +catAtPosition.getName()+" "+ catAtPosition.getId());
        holder.name.setText(catAtPosition.getName());
        holder.cat_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(catAtPosition.getId());
                Intent intent = new Intent(view.getContext(), CatDetailActivity.class);
                intent.putExtra("catID",catAtPosition.getId());
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount(){
        return favourites.size();
    }

}
