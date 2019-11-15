package com.example.hw3cat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CatDatabase {

    public static HashMap<String, Cat> cats;

    static{
        cats = new HashMap<>();
    }

    public static ArrayList<Cat> getFavourites(){
        ArrayList<Cat> favouriteCatsArray = new ArrayList<>();
        for (Map.Entry<String, Cat> favouriteCat :
                cats.entrySet()) {
            favouriteCatsArray.add(favouriteCat.getValue());
        }
        return favouriteCatsArray;
    }

    public static void addToFavourites(Cat newCat){
        String catId = newCat.getId();
        cats.put(catId, newCat);
    }

    public static void removeFromFavourite(Cat newCat){
        String catId = newCat.getId();
        cats.remove(catId);
    }

}
