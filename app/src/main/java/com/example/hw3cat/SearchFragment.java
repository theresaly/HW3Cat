package com.example.hw3cat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class SearchFragment extends Fragment {

    SearchView catSearch;
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;

    public SearchFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_search_recycler, container, false);

        final RequestQueue requestQueue = Volley.newRequestQueue(view.getContext());
        String url = "https://api.thecatapi.com/v1/breeds?api_key=8d70b17a-0abe-4254-a5f0-f9ca103afd37";
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Cat>>(){}.getType();
                Collection<Cat> catAPI =gson.fromJson(response, collectionType);
                requestQueue.stop();
                ArrayList<Cat> catsList = new ArrayList<>(catAPI);

                recyclerView = view.findViewById(R.id.searchRecycler);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(layoutManager);
                searchAdapter = new SearchAdapter(catsList);
                recyclerView.setAdapter(searchAdapter);

                catSearch = view.findViewById(R.id.search);
                search(catSearch);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Search Error: " + error);
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
        return view;
    }

    private void search(SearchView search) {

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return true;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return true;
            }
        });
    }
}
