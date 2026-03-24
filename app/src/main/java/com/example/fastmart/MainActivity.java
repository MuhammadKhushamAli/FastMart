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

    boolean firstTime = false;

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
        init(savedInstanceState);
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

    private void init(Bundle savedInstanceState) {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            homeFrag = new HomeFragment();
            browseFrag = new BrowseFragment();
            favoriteFrag = new FavoriteFragment();
            cartFrag = new CartFragment();
            profileFrag = new ProfileFragment();


            int currentFragID = R.id.currentFragment;

            fragmentManager.beginTransaction()
                    .add(currentFragID, homeFrag, KeyUtils.homeFragTag)
                    .add(currentFragID, browseFrag, KeyUtils.browseFragTag).hide(browseFrag)
                    .add(currentFragID, favoriteFrag, KeyUtils.favoriteFragTag).hide(favoriteFrag)
                    .add(currentFragID, cartFrag, KeyUtils.cartFragTag).hide(cartFrag)
                    .add(currentFragID, profileFrag, KeyUtils.profileFragTag).hide(profileFrag)
                    .commit();
            activeFrag = homeFrag;
            selectedFrag = null;
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
        else {
            homeFrag = fragmentManager.findFragmentByTag(KeyUtils.homeFragTag);
            browseFrag = fragmentManager.findFragmentByTag(KeyUtils.browseFragTag);
            favoriteFrag = fragmentManager.findFragmentByTag(KeyUtils.favoriteFragTag);
            cartFrag = fragmentManager.findFragmentByTag(KeyUtils.cartFragTag);
            profileFrag = fragmentManager.findFragmentByTag(KeyUtils.profileFragTag);


            if (homeFrag != null && homeFrag.isVisible()) {
                activeFrag = homeFrag;
                bottomNavigationView.setSelectedItemId(R.id.home);
            }
            else if (browseFrag != null && browseFrag.isVisible()) {
                activeFrag = browseFrag;
                bottomNavigationView.setSelectedItemId(R.id.browse);
            }
            else if (favoriteFrag != null && favoriteFrag.isVisible()) {
                activeFrag = favoriteFrag;
                bottomNavigationView.setSelectedItemId(R.id.favorite);
            }
            else if (cartFrag != null && cartFrag.isVisible()) {
                activeFrag = cartFrag;
                bottomNavigationView.setSelectedItemId(R.id.cart);
            }
            else if (profileFrag != null && profileFrag.isVisible()) {
                activeFrag = profileFrag;
                bottomNavigationView.setSelectedItemId(R.id.profile);
            }
            selectedFrag = null;
        }
    }
}