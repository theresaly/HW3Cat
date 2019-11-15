package com.example.hw3cat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class CatDetailActivity extends AppCompatActivity {

    TextView name;
    ImageView image;
    ImageButton favourite_btn;
    TextView description;
    TextView origin;
    TextView temperament;
    TextView life_span;
    TextView dog_friendly;
    TextView weight;
    TextView wikipedia_link;
    Cat cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_detail);

        final Context context = this;
        name = findViewById(R.id.name);
        image = findViewById(R.id.image);
        description = findViewById(R.id.description);
        origin = findViewById(R.id.origin);
        temperament = findViewById(R.id.temperament);
        life_span = findViewById(R.id.life_span);
        dog_friendly = findViewById(R.id.dog_friendly);
        weight = findViewById(R.id.weight);
        wikipedia_link = findViewById(R.id.wikipedia_url);
        favourite_btn = findViewById(R.id.favourite_btn);


        Intent intent = getIntent();
        final String intentCat = intent.getStringExtra("catID");
        String url = "https://api.thecatapi.com/v1/images/search?api_key=8d70b17a-0abe-4254-a5f0-f9ca103afd37&breed_id=" + intentCat;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<String> responseListener =new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);

                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<Cat>>(){}.getType();
                ArrayList<Cat> catList =gson.fromJson(response, collectionType);
                requestQueue.stop();
                Cat api = catList.get(0);
                cat = api.getBreeds().get(0);

                name.setText(cat.getName());
                Glide.with(context).load(api.getUrl()).into(image);
                description.setText(cat.getDescription());
                origin.setText("Origin: "+cat.getOrigin());
                temperament.setText("Temperament: \n"+cat.getTemperament());
                life_span.setText("Lifespan: "+cat.getLife_span());
                weight.setText("Weight: "+cat.getWeight().getWeight());
                dog_friendly.setText("Dog Friendliness: "+cat.getDog_friendly());
                wikipedia_link.setText("Wikipedia Link: \n"+cat.getWikipedia_url());

                if (CatDatabase.cats.containsKey(intentCat)){
                    favourite_btn.setImageResource(R.drawable.ic_favorite_black_24dp);
                    favourite_btn.setTag(true);
                } else {
                    favourite_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    favourite_btn.setTag(false);
                }


            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Volley Error: " + error);
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, responseListener, errorListener);
        requestQueue.add(stringRequest);
    }

    public void toggleBtn(View view){
        if (favourite_btn.getTag().equals(true)){
            CatDatabase.removeFromFavourite(cat);
            favourite_btn.setTag(false);
            favourite_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        } else {
            CatDatabase.addToFavourites(cat);
            favourite_btn.setTag(true);
            favourite_btn.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

    }
}
