package com.example.hw3cat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SearchFragment();
        swapFragment(fragment);

        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new SearchFragment();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_favourites) {
                    Fragment fragment = new FavouritesFragment();

                    Bundle bundle = new Bundle();
                    bundle.putString("param1", "First Argument");
                    bundle.putString("param2", "Second Argument");
                    fragment.setArguments(bundle);

                    swapFragment(fragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

}



/*import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //Create Xml View Links
    BottomNavigationView bottomNavigationView;

    //Create Fragment Managers and Transactions
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    //Create Fragments
    SearchFragment searchFragment;
    FavouritesFragment favouritesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialise Views
        bottomNavigationView = findViewById(R.id.nav_view);

        //Initialise Fragments
        searchFragment = new SearchFragment();
        favouritesFragment = new FavouritesFragment();

        //Initialise Managers and Transactions
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, searchFragment);
        fragmentTransaction.commit();

        //Bottom Navigation - create onClick Listeners
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.nav_search:
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_slot, searchFragment);
                                fragmentTransaction.commit();
                                return true;
                            case R.id.nav_favourites:
                                FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                                fragmentTransaction1.replace(R.id.fragment_slot, favouritesFragment);
                                fragmentTransaction1.commit();
                                return true;
                        }
                        return false;
                    }
                });
    }
}*/
