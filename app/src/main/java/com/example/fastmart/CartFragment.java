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
import android.widget.Toast;

public class CartFragment extends Fragment {
    MyApplication app;
    CartItemRecyclerAdapter adapter;

    public CartFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();
        app = (MyApplication) context.getApplicationContext();

        RecyclerView recyclerView = view.findViewById(R.id.cart_section_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        adapter = new CartItemRecyclerAdapter(context, app.cart);

        recyclerView.setAdapter(adapter);

    }

    public void addToCart(Item item) {
        if (app.cart.contains(item)) {
            item.incItemsSelected();
            adapter.notifyItemChanged(app.cart.indexOf(item));
        }
        else {
            app.cart.add(item);
            adapter.notifyItemInserted(app.cart.size());
        }
    }
}