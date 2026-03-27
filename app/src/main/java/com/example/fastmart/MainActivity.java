package com.example.fastmart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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


public class MainActivity extends AppCompatActivity
        implements HomeFragment.setOnclickListener,
        FavoriteFragment.setOnClickListener,
        DealOfDayFragment.setOnClickListener,
        BrowseFragment.setOnClickListener{
    MyApplication app;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFrag;
    Fragment browseFrag;
    FavoriteFragment favoriteFrag;
    CartFragment cartFrag;
    Fragment profileFrag;
    Fragment activeFrag;
    Fragment selectedFrag;

    FragmentManager fragmentManager;
    boolean revNav = false;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (activeFrag != null) {
            outState.putString(KeyUtils.activeStateTag, activeFrag.getTag());
        }
    }

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
            if (revNav) {
                return true;
            }

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
                        .addToBackStack(null)
                        .commit();
                activeFrag = selectedFrag;
                selectedFrag = null;
                return true;
            }
            return false;
        });

        fragmentManager.addOnBackStackChangedListener(() -> {
            activeFrag = null;
            int id = 0;
            if (homeFrag.isVisible()) {
                activeFrag = homeFrag;
                id = R.id.home;
            }
            else if (browseFrag.isVisible()) {
                activeFrag = browseFrag;
                id = R.id.browse;
            }
            else if (favoriteFrag.isVisible()) {
                activeFrag = favoriteFrag;
                id = R.id.favorite;
            }
            else if (cartFrag.isVisible()) {
                activeFrag = cartFrag;
                id = R.id.cart;
            }
            else if (profileFrag.isVisible()) {
                activeFrag = profileFrag;
                id = R.id.profile;
            }

            revNav = true;
            bottomNavigationView.setSelectedItemId(id);
            revNav = false;
        });
    }

    private void init(Bundle savedInstanceState) {
        app = (MyApplication) getApplicationContext();

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
            String activeStateTag = savedInstanceState.getString(KeyUtils.activeStateTag);
            activeFrag = fragmentManager.findFragmentByTag(activeStateTag);
            homeFrag = (HomeFragment) fragmentManager.findFragmentByTag(KeyUtils.homeFragTag);
            browseFrag = fragmentManager.findFragmentByTag(KeyUtils.browseFragTag);
            favoriteFrag = (FavoriteFragment) fragmentManager.findFragmentByTag(KeyUtils.favoriteFragTag);
            cartFrag = (CartFragment) fragmentManager.findFragmentByTag(KeyUtils.cartFragTag);
            profileFrag = fragmentManager.findFragmentByTag(KeyUtils.profileFragTag);
            selectedFrag = null;
        }
    }

    @Override
    public void addFav(int position) {
        favoriteFrag.addToWishList(app.items.get(position));
    }

    @Override
    public void removeFav(int position) {
        favoriteFrag.removeFromWishlist(app.items.get(position));
    }

    @Override
    public void notifyItemChanged(Item item) {
        homeFrag.notifyChange(item);
    }

    @Override
    public void addToCart(Item item) {
        cartFrag.addToCart(item);
    }

    @Override
    public void addToFav(Item item) {
        favoriteFrag.addToWishList(item);
    }

    @Override
    public void removeFromFav(Item item) {
        favoriteFrag.removeFromWishlist(item);
    }

    @Override
    public void goBack() {
        fragmentManager.popBackStack();
    }
}