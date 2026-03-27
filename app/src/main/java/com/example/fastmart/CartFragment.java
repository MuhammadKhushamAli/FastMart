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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CartFragment extends Fragment
        implements CartItemRecyclerAdapter.setOnClickListener  {
    MyApplication app;
    CartItemRecyclerAdapter adapter;
    TextView shippingValueField;
    TextView totalPriceField;
    MaterialButton checkoutBtn;
    float shippingCost;

    public CartFragment() {
        shippingCost = 0.00f;
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
        shippingValueField = view.findViewById(R.id.shipping_in_cart);
        shippingValueField.setText("$ " + shippingCost);

        totalPriceField = view.findViewById(R.id.total_price_in_cart);

        RecyclerView recyclerView = view.findViewById(R.id.cart_section_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        adapter = new CartItemRecyclerAdapter(context, app.cart, this);

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

    @Override
    public void calculateTotal() {
        float totalPrice = 0.00f;
        ArrayList<Item> cart = app.cart;
        for (var item: cart) {
            if (item.getDiscountedPrice() != 0.00f) {
                totalPrice += item.getDiscountedPrice() * item.getItemsSelected();
            }
            else {
                totalPrice += item.getPrice() * item.getItemsSelected();
            }
        }
        totalPriceField.setText("$ " + totalPrice);
    }
}