package com.example.fastmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItemRecyclerAdapter extends RecyclerView.Adapter<CartItemRecyclerAdapter.CartItemViewHolder> {
    Context context;
    ArrayList<Item> itemArrayList;

    public CartItemRecyclerAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.cartItemImage.setImageResource(item.getImageID());
        holder.cartItemPrice.setText(item.getPrice());
        holder.cartItemName.setText(item.getName());
        holder.cartItemModel.setText(item.getModel());
        holder.cartItemColor.setText(item.getColor());
        holder.cartItemCount.setText(item.getItemsSelected());

        holder.cartIncButton.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeInc = itemArrayList.get(pos);

                if (itemToBeInc.getItemsSelected() < itemToBeInc.getItemsAvailable()) {
                    itemToBeInc.incItemsSelected();
                }
            }
        });
        holder.cartDecButton.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeDec = itemArrayList.get(pos);

                if (itemToBeDec.getItemsSelected() > 1) {
                    itemToBeDec.decItemsSelected();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cartItemImage;
        TextView cartItemPrice;
        TextView cartItemName;
        TextView cartItemModel;
        TextView cartItemColor;
        ImageButton cartMoreButton;
        ImageButton cartIncButton;
        ImageButton cartDecButton;
        TextView cartItemCount;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cartItemImage = itemView.findViewById(R.id.cart_item_img);
            cartItemPrice = itemView.findViewById(R.id.cart_item_price);
            cartItemName = itemView.findViewById(R.id.cart_item_name);
            cartItemModel = itemView.findViewById(R.id.cart_item_model);
            cartItemColor = itemView.findViewById(R.id.cart_item_color);
            cartMoreButton = itemView.findViewById(R.id.cart_more);
            cartIncButton = itemView.findViewById(R.id.cart_inc);
            cartDecButton = itemView.findViewById(R.id.cart_dec);
            cartItemCount = itemView.findViewById(R.id.cart_item_count);
        }
    }
}
