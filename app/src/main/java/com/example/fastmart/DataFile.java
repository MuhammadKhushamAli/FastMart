package com.example.fastmart;


import java.util.ArrayList;

public class DataFile {
    static public class ItemCard {
        int imageID;
        String price;
        String name;
        String model;
        String color;

        ItemCard(int imageID, String price, String name, String model, String color) {
            this.imageID = imageID;
            this.price = price;
            this.name = name;
            this.model = model;
            this.color = color;
        }
    }
    static public final String dodCatagory = "Microphone";
    static public final String dodPrice = "$123";
    static public final String dodName = "Microphone";
    static public final String dodDescription = "Dynamic Microphone";

    static public ArrayList<ItemCard> getItems() {
        ArrayList<ItemCard> items = new ArrayList<>();
        items.add(new ItemCard(R.drawable.headphone1, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone2, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone3, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone4, "$1234", "Sony", "m-123","Black"));
        return items;
    }

}
