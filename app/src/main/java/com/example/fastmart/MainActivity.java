package com.example.fastmart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment homeFrag;
    Fragment browseFrag;
    Fragment favoriteFrag;
    Fragment cartFrag;
    Fragment profileFrag;

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

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                openFragment(homeFrag);
                return true;
            }
            else if (id == R.id.browse) {
                openFragment(browseFrag);
                return true;
            }
            else if (id == R.id.favorite) {
                openFragment(favoriteFrag);
                return true;
            }
            else if (id == R.id.cart) {
                openFragment(cartFrag);
                return true;
            }
            else if (id == R.id.profile) {
                openFragment(profileFrag);
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
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.currentFragment, fragment)
                .commit();
    }
}