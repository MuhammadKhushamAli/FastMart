package com.example.fastmart;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemHolder> {
    Context context;
    ArrayList<Item> itemArrayList;
    setOnClickListener listener;

    public interface setOnClickListener {
        public void favAdd(int position);
        public void  favRemove(int position);
    }
    public ItemsListAdapter(Context context, ArrayList<Item> itemArrayList, setOnClickListener listener) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Item item = itemArrayList.get(position);
        holder.itemImage.setImageResource(item.getImageID());
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(item.getPrice());
        holder.itemModel.setText(item.getModel());
        holder.itemColor.setText(item.getColor());

        if (item.getIsFavorite()) {
            holder.favBtn.setImageResource(R.drawable.favorite_filled);
        }
        else {
            holder.favBtn.setImageResource(R.drawable.favorite);
        }

        holder.favBtn.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeFav = itemArrayList.get(pos);

                if (itemToBeFav.getIsFavorite())
                {
                    holder.favBtn.setImageResource(R.drawable.favorite);
                    itemToBeFav.setIsFavorite(false);
                    listener.favRemove(pos);
                }
                else {
                    holder.favBtn.setImageResource(R.drawable.favorite_filled);
                    itemToBeFav.setIsFavorite(true);
                    listener.favAdd(pos);
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemPrice;
        TextView itemName;
        TextView itemModel;
        TextView itemColor;
        ImageButton favBtn;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemName = itemView.findViewById(R.id.item_name);
            itemModel = itemView.findViewById(R.id.item_model);
            itemColor = itemView.findViewById(R.id.item_color);
            favBtn = itemView.findViewById(R.id.fav_img);
        }
    }
}