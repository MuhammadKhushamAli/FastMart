package com.example.fastmart;

public class Item {
    private int imageID;
    private String price;
    private String name;
    private String model;
    private String color;
    private String description;
    private String discountedPrice;
    private String category;
    private Boolean isFavorite;
    private int itemsAvailable;
    private int itemsSelected;


    Item(int imageID,
         String price,
         String name,
         String model,
         String color,
         String description,
         String discountedPrice,
         String category,
         int itemsAvailable) {
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


    Item(int imageID,
         String price,
         String name,
         String model,
         String color,
         String description,
         String category,
         int itemsAvailable) {
        this(imageID,
                price,
                name,
                model,
                color,
                description,
                "",
                category,
                itemsAvailable);
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
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
}
