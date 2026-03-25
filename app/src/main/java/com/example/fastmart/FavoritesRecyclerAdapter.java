package com.example.fastmart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder> {
    Context context;
    ArrayList<Item> itemArrayList;

    public FavoritesRecyclerAdapter(Context context, ArrayList<Item> itemArrayList) {
        this.context = context;
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favourite_item_layout, parent, false);
        return new FavoritesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.favItemImg.setImageResource(item.getImageID());
        holder.favItemPrice.setText(item.getPrice());
        holder.favItemName.setText(item.getName());
        holder.favItemModel.setText(item.getModel());
        holder.favItemColor.setText(item.getColor());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView favItemImg;
        TextView favItemPrice;
        TextView favItemName;
        TextView favItemModel;
        TextView favItemColor;
        ImageButton favToCart;
        ImageButton favMore;
        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            favItemImg = itemView.findViewById(R.id.fav_item_img);
            favItemPrice = itemView.findViewById(R.id.fav_item_price);
            favItemName = itemView.findViewById(R.id.fav_item_name);
            favItemModel = itemView.findViewById(R.id.fav_item_model);
            favItemColor = itemView.findViewById(R.id.fav_item_color);
            favToCart = itemView.findViewById(R.id.fav_to_cart);
            favMore = itemView.findViewById(R.id.fav_more);
        }
    }
}
