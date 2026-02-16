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
    static public final String dodDescription = "A microphone is an essential audio device that captures sound waves and converts them into electrical signals for recording, amplification, or broadcasting. It allows for clear voice communication, high-quality audio recording, and seamless sound transmission in a variety of settings—whether in professional studios, live performances, podcasts, gaming, or virtual meetings. Modern microphones come in various types, including dynamic, condenser, and USB models, each designed to suit different audio needs. With features like noise cancellation, cardioid pickup patterns, and plug-and-play functionality, microphones ensure crisp, accurate, and reliable sound capture for both amateurs and professionals alike.";
    static public final String dodModel = "123-A";
    static public final String dodColor = "Black";

    static public ArrayList<ItemCard> getItems() {
        ArrayList<ItemCard> items = new ArrayList<>();
        items.add(new ItemCard(R.drawable.headphone1, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone2, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone3, "$1234", "Sony", "m-123","Black"));
        items.add(new ItemCard(R.drawable.headphone4, "$1234", "Sony", "m-123","Black"));
        return items;
    }

}
