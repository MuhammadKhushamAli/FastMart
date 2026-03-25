package com.example.fastmart;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    MyApplication app;
    FavoritesRecyclerAdapter adapter;
    RecyclerView recyclerView;

    public FavoriteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();
        app = (MyApplication) context.getApplicationContext();

        recyclerView = view.findViewById(R.id.favorites_section_recycler);

        adapter = new FavoritesRecyclerAdapter(context, app.wishlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public void addToWishList(Item item) {
        app.wishlist.add(item);
        adapter.notifyItemInserted(app.wishlist.size());
    }
    public void removeFromWishlist(Item item) {
        int pos = app.wishlist.indexOf(item);
        app.wishlist.remove(item);
        adapter.notifyItemRemoved(pos);
    }
}