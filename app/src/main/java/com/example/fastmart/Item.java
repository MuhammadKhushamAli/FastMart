package com.example.fastmart;

public class Item {
    private int imageID;
    private String price;
    private String name;
    private String model;
    private String color;
    private String description;
    private String discountedPrice;


    Item(int imageID, String price, String name, String model, String color, String description, String discountedPrice = "") {
        this.imageID = imageID;
        this.price = price;
        this.name = name;
        this.model = model;
        this.color = color;
        this.description = description;
        this.discountedPrice = discountedPrice;
    }
    Item(int imageID, String price, String name, String model, String color, String description) {
        this(imageID, price, name, model, color, description, "");
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
}
