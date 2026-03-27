package com.example.fastmart;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Item implements Parcelable {
    private int id;
    private int imageID;
    private float price;
    private String name;
    private String model;
    private String color;
    private String description;
    private float discountedPrice;
    private String category;
    private Boolean isFavorite;
    private int itemsAvailable;
    private int itemsSelected;


    Item(int id,
         int imageID,
         float price,
         String name,
         String model,
         String color,
         String description,
         float discountedPrice,
         String category,
         int itemsAvailable) {
        this.id = id;
        this.imageID = imageID;
        this.price = price;
        this.name = name;
        this.model = model;
        this.color = color;
        this.description = description;
        this.discountedPrice = discountedPrice;
        this.category = category;
        this.isFavorite = false;
        this.itemsAvailable = itemsAvailable;
        this.itemsSelected = 1;
    }


    Item(int id,
         int imageID,
         float price,
         String name,
         String model,
         String color,
         String description,
         String category,
         int itemsAvailable) {
        this(id,
                imageID,
                price,
                name,
                model,
                color,
                description,
                0.00f,
                category,
                itemsAvailable);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.model = in.readString();
        this.color = in.readString();
        this.description = in.readString();
        this.price = in.readFloat();
        this.discountedPrice = in.readFloat();
        this.imageID = in.readInt();
        this.itemsAvailable = in.readInt();
        this.itemsSelected = in.readInt();
        this.id = in.readInt();
    }
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public Boolean getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public int getItemsAvailable() {
        return itemsAvailable;
    }

    public void setItemsAvailable(int itemsAvailable) {
        this.itemsAvailable = itemsAvailable;
    }

    public int getItemsSelected() {
        return itemsSelected;
    }

    public void incItemsSelected() {
        itemsSelected++;
    }

    public void decItemsSelected() {
        itemsSelected--;
    }

    public void setItemsSelected(int itemsSelected) {
        this.itemsSelected = itemsSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(model);
        parcel.writeString(color);
        parcel.writeString(description);
        parcel.writeFloat(price);
        parcel.writeFloat(discountedPrice);
        parcel.writeInt(imageID);
        parcel.writeInt(itemsAvailable);
        parcel.writeInt(itemsSelected);
        parcel.writeInt(id);
    }
    public static final Creator<Item> CREATOR = new Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        @Override
        public Item[] newArray(int i) {
            return new Item[i];
        }
    };
}
