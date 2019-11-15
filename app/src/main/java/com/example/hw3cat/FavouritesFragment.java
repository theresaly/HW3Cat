package com.example.hw3cat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FavouritesFragment extends Fragment {

    RecyclerView recyclerView;
    FavouriteAdapter favouriteAdapter;

    public FavouritesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favourites_recycler, container, false);
        recyclerView = view.findViewById(R.id.fav_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        favouriteAdapter = new FavouriteAdapter(CatDatabase.getFavourites());
        recyclerView.setAdapter(favouriteAdapter);

        return view;
    }

}
