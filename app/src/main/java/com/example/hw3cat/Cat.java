package com.example.hw3cat;

import java.util.ArrayList;

public class Cat {

    String id;
    String name;
    String image;
    String description;
    Weight weight;
    String temperament;
    String origin;
    String life_span;
    String dog_friendly;
    String wikipedia_url;
    ArrayList<Cat> breeds;
    String url;

    public ArrayList<Cat> getBreeds() {
        return breeds;
    }

    public Cat(String id, String name, String description, Weight weight, String temperament, String origin, String life_span, String dog_friendly, String wikipedia_url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.dog_friendly = dog_friendly;
        this.wikipedia_url = wikipedia_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getUrl() {
        return url;
    }


    public class Weight{
        String weight;

        public Weight(String weight) {
            this.weight = weight;
        }

        public String getWeight() {
            return weight;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }

}
