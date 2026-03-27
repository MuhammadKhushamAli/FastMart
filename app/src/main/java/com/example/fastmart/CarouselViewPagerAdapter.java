package com.example.fastmart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarouselViewPagerAdapter extends RecyclerView.Adapter<CarouselViewPagerAdapter.CarouselViewHolder> {
    Context context;
    ArrayList<Item> itemArrayList;
    setOnCLickListener listener;
    public interface setOnCLickListener {
        public void addToFav(Item item);
        public void removeFromFav(Item item);
    }
    public CarouselViewPagerAdapter(Context context, ArrayList<Item> itemArrayList, setOnCLickListener listener) {
        this.context = context;
        this.itemArrayList = itemArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CarouselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.carousel_layout, parent, false);
        return new CarouselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarouselViewHolder holder, int position) {
        Item carouselItem = itemArrayList.get(position);
        holder.carouselItemImage.setImageResource(carouselItem.getImageID());
        holder.carouselItemCategory.setText(carouselItem.getCategory());
        holder.carouselItemDiscountedPrice.setText("$ " + carouselItem.getDiscountedPrice());
        holder.carouselItemOriginalPrice.setText("$ " + carouselItem.getPrice());
        holder.carouselItemName.setText(carouselItem.getName());
        holder.carouselItemDesc.setText(carouselItem.getDescription());

        holder.favBtn.setOnClickListener((v) -> {
            int pos = holder.getAbsoluteAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                Item itemToBeFav = itemArrayList.get(position);

                if (itemToBeFav.getIsFavorite())
                {
                    holder.favBtn.setImageResource(R.drawable.favorite);
                    itemToBeFav.setIsFavorite(false);
                    listener.removeFromFav(itemToBeFav);
                }
                else {
                    holder.favBtn.setImageResource(R.drawable.favorite_filled);
                    itemToBeFav.setIsFavorite(true);
                    listener.addToFav(itemToBeFav);
                }
            }
        });
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailedView.class);
            intent.putExtra(KeyUtils.imageIDKey, carouselItem.getImageID())
                    .putExtra(KeyUtils.nameKey, carouselItem.getName())
                    .putExtra(KeyUtils.descriptionKey, carouselItem.getDescription())
                    .putExtra(KeyUtils.colorKey, carouselItem.getColor())
                    .putExtra(KeyUtils.modelKey, carouselItem.getModel())
                    .putExtra(KeyUtils.priceKey, carouselItem.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public static class CarouselViewHolder extends RecyclerView.ViewHolder {
        ImageView carouselItemImage;
        TextView carouselItemCategory;
        TextView carouselItemDiscountedPrice;
        TextView carouselItemOriginalPrice;
        TextView carouselItemName;
        TextView carouselItemDesc;
        ImageButton favBtn;


        public CarouselViewHolder(@NonNull View itemView) {
            super(itemView);
            carouselItemImage = itemView.findViewById(R.id.carousel_image);
            carouselItemCategory = itemView.findViewById(R.id.carousel_category);
            carouselItemDiscountedPrice = itemView.findViewById(R.id.carousel_discounted_price);
            carouselItemOriginalPrice = itemView.findViewById(R.id.carousel_original_price);
            carouselItemName = itemView.findViewById(R.id.carousel_item_name);
            carouselItemDesc = itemView.findViewById(R.id.carousel_item_description);
            favBtn = itemView.findViewById(R.id.fav_img_carousel);
            favBtn.setImageResource(R.drawable.favorite);
        }
    }
}
