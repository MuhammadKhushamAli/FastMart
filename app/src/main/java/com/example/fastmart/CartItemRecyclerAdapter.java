package com.example.fastmart;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.ColorContrast;

import java.util.ArrayList;

public class CartItemRecyclerAdapter extends RecyclerView.Adapter<CartItemRecyclerAdapter.CartItemViewHolder> {
    Context context;
    ArrayList<Item> itemArrayList;
    setOnClickListener listener;

    public interface setOnClickListener {
        public void calculateTotal();
        public void notifyDeleteItem();
    }
    public CartItemRecyclerAdapter(Context context, ArrayList<Item> itemArrayList, setOnClickListener listener) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.listener = listener;
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

        String originalPrice = "$ " + item.getPrice();
        holder.cartItemOriginalPrice.setText(originalPrice);
        holder.cartItemName.setText(item.getName());
        holder.cartItemModel.setText(item.getModel());
        holder.cartItemColor.setText(item.getColor());
        holder.cartItemCount.setText(String.valueOf(item.getItemsSelected()));
        holder.cartIncButton.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeInc = itemArrayList.get(pos);

                if (itemToBeInc.getItemsSelected() < itemToBeInc.getItemsAvailable()) {
                    itemToBeInc.incItemsSelected();
                    notifyItemChanged(pos);
                }
            }
        });
        holder.cartDecButton.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeDec = itemArrayList.get(pos);

                if (itemToBeDec.getItemsSelected() > 1) {
                    itemToBeDec.decItemsSelected();
                    notifyItemChanged(pos);
                }
            }
        });
        holder.cartMoreButton.setOnClickListener(v -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeRemoved = itemArrayList.get(pos);
                itemToBeRemoved.setItemsSelected(1);
                itemArrayList.remove(pos);
                notifyItemRemoved(pos);
                listener.notifyDeleteItem();
            }
        });

        if (item.getDiscountedPrice() != 0) {
            holder.cartItemDiscountedPrice.setTextColor(Color.RED);
            String discountedPrice = "$ " + item.getDiscountedPrice();
            holder.cartItemDiscountedPrice.setText(discountedPrice);
            holder.cartItemOriginalPrice.setPaintFlags(
                    holder.cartItemOriginalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG
            );
            holder.cartItemOriginalPrice.setTextColor(
                    ContextCompat.getColor(context, R.color.discounted_tag_color)
                    );
            holder.cartItemOriginalPrice.setTypeface(null, Typeface.NORMAL);
            holder.cartItemOriginalPrice.setTextSize(11);
            holder.cartItemDiscountedPrice.setVisibility(View.VISIBLE);
        }
        else {
            holder.cartItemOriginalPrice.setTextColor(
                    Color.rgb(255,255,255)
            );
            holder.cartItemOriginalPrice.setPaintFlags(
                    holder.cartItemDiscountedPrice.getPaintFlags()
            );
            holder.cartItemOriginalPrice.setTypeface(null, Typeface.BOLD);
            holder.cartItemOriginalPrice.setTextSize(15);
            holder.cartItemDiscountedPrice.setVisibility(View.GONE);
        }

        listener.calculateTotal();
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class CartItemViewHolder extends RecyclerView.ViewHolder {
        ImageView cartItemImage;
        TextView cartItemOriginalPrice;
        TextView cartItemDiscountedPrice;
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
            cartItemOriginalPrice = itemView.findViewById(R.id.cart_original_item_price);
            cartItemDiscountedPrice = itemView.findViewById(R.id.cart_discounted_item_price);
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
