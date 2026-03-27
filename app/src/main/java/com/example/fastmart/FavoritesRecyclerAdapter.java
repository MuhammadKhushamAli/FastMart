package com.example.fastmart;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesRecyclerAdapter extends RecyclerView.Adapter<FavoritesRecyclerAdapter.FavoritesViewHolder> {
    Context context;
    ArrayList<Item> itemArrayList;

    setOnClickListener listener;
    public interface setOnClickListener {
        public void notifyChange(Item item);
        public void addToCart(Item item);
    }
    public FavoritesRecyclerAdapter(Context context, ArrayList<Item> itemArrayList, setOnClickListener listener) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.listener = listener;
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

        String originalPrice = "$ " + item.getPrice();
        holder.favItemOriginalPrice.setText(originalPrice);

        holder.favItemName.setText(item.getName());
        holder.favItemModel.setText(item.getModel());
        holder.favItemColor.setText(item.getColor());

        if (item.getDiscountedPrice() != 0) {
            holder.favItemOriginalPrice.setTextColor(Color.RED);

            String discountedPrice = "$ " + item.getDiscountedPrice();
            holder.favItemDiscountedPrice.setText(discountedPrice);
            holder.favItemDiscountedPrice.setPaintFlags(
                    holder.favItemDiscountedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );
            holder.favItemDiscountedPrice.setVisibility(View.VISIBLE);
        }
        else {
            holder.favItemOriginalPrice.setTextColor(holder.favItemDiscountedPrice.getTextColors());
            holder.favItemDiscountedPrice.setVisibility(View.GONE);
        }

        holder.favMore.setOnClickListener(v -> {
            int pos = holder.getAbsoluteAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeRemoved = itemArrayList.get(pos);

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Do you want to delete this product from favorites?")
                        .setPositiveButton("Yes", (a, b) -> {
                            itemArrayList.remove(itemToBeRemoved);
                            itemToBeRemoved.setIsFavorite(false);
                            notifyItemRemoved(pos);
                            listener.notifyChange(itemToBeRemoved);
                        })
                        .setNegativeButton("No", (a, b) -> {});
                builder.create().show();;
            }
        });

        holder.favToCart.setOnClickListener(v -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.addToCart(itemArrayList.get(pos));
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        ImageView favItemImg;
        TextView favItemOriginalPrice;
        TextView favItemDiscountedPrice;
        TextView favItemName;
        TextView favItemModel;
        TextView favItemColor;
        ImageButton favToCart;
        ImageButton favMore;
        public FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            favItemImg = itemView.findViewById(R.id.fav_item_img);
            favItemOriginalPrice = itemView.findViewById(R.id.fav_original_item_price);
            favItemDiscountedPrice = itemView.findViewById(R.id.fav_discounted_item_price);
            favItemName = itemView.findViewById(R.id.fav_item_name);
            favItemModel = itemView.findViewById(R.id.fav_item_model);
            favItemColor = itemView.findViewById(R.id.fav_item_color);
            favToCart = itemView.findViewById(R.id.fav_to_cart);
            favMore = itemView.findViewById(R.id.fav_more);
        }
    }
}
