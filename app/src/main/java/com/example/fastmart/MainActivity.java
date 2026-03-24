package com.example.fastmart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment homeFrag;
    Fragment browseFrag;
    Fragment favoriteFrag;
    Fragment cartFrag;
    Fragment profileFrag;
    Fragment activeFrag;
    Fragment selectedFrag;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                selectedFrag = homeFrag;
            }
            else if (id == R.id.browse) {
                selectedFrag = browseFrag;
            }
            else if (id == R.id.favorite) {
                selectedFrag = favoriteFrag;
            }
            else if (id == R.id.cart) {
                selectedFrag = cartFrag;
            }
            else if (id == R.id.profile) {
                selectedFrag = profileFrag;
            }
            if (selectedFrag != null && selectedFrag != activeFrag)
            {
                fragmentManager.beginTransaction()
                        .hide(activeFrag)
                        .show(selectedFrag)
                        .commit();
                activeFrag = selectedFrag;
                selectedFrag = null;
                return true;
            }
            return false;
        });

    }

    private void init() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        homeFrag = new HomeFragment();
        browseFrag = new BrowseFragment();
        favoriteFrag = new FavoriteFragment();
        cartFrag = new CartFragment();
        profileFrag = new ProfileFragment();

        fragmentManager = getSupportFragmentManager();

        int currentFragID = R.id.currentFragment;

        fragmentManager.beginTransaction()
                .add(currentFragID, homeFrag)
                .add(currentFragID, browseFrag).hide(browseFrag)
                .add(currentFragID, favoriteFrag).hide(favoriteFrag)
                .add(currentFragID, cartFrag).hide(cartFrag)
                .add(currentFragID, profileFrag).hide(profileFrag)
                .commit();
        activeFrag = homeFrag;
        selectedFrag = null;
        bottomNavigationView.setSelectedItemId(R.id.home);
    }
}