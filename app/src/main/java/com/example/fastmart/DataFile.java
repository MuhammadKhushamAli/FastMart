package com.example.fastmart;


import java.util.ArrayList;
import java.util.Arrays;

public class DataFile {
    static public class ItemCard {
        int imageID;
        String price;
        String name;
        String model;
        String color;
        String description;

        ItemCard(int imageID, String price, String name, String model, String color, String description) {
            this.imageID = imageID;
            this.price = price;
            this.name = name;
            this.model = model;
            this.color = color;
            this.description = description;
        }
    }
    static public final String phoneNumber = "03107488488";
    static public final String dodCatagory = "Microphone";
    static public final String dodPrice = "$123";
    static public final String dodName = "Microphone";
    static public final String dodDescription = "A microphone is an essential audio device that captures sound waves and converts them into electrical signals for recording, amplification, or broadcasting. It allows for clear voice communication, high-quality audio recording, and seamless sound transmission in a variety of settings—whether in professional studios, live performances, podcasts, gaming, or virtual meetings. Modern microphones come in various types, including dynamic, condenser, and USB models, each designed to suit different audio needs. With features like noise cancellation, cardioid pickup patterns, and plug-and-play functionality, microphones ensure crisp, accurate, and reliable sound capture for both amateurs and professionals alike.";
    static public final String dodModel = "123-A";
    static public final String dodColor = "Black";

    static public ArrayList<ItemCard> items = new ArrayList<>(Arrays.asList(
        new ItemCard(R.drawable.headphone1, "$1234", "Sony", "m-123","Black", "Headphones are audio devices designed to deliver immersive sound directly to your ears, offering a personal and high-quality listening experience. They are ideal for music, gaming, calls, podcasts, and multimedia consumption. Modern headphones come in various types, including over-ear, on-ear, and in-ear, each providing comfort, noise isolation, and superior sound clarity. Features like wireless connectivity, active noise cancellation, built-in microphones, and long-lasting battery life enhance convenience and audio performance. Whether for professional use, entertainment, or travel, headphones combine style, comfort, and exceptional sound fidelity to bring your audio experience to life."),
        new ItemCard(R.drawable.headphone2, "$1234", "Sony", "m-123","Black", "Headphones are audio devices designed to deliver immersive sound directly to your ears, offering a personal and high-quality listening experience. They are ideal for music, gaming, calls, podcasts, and multimedia consumption. Modern headphones come in various types, including over-ear, on-ear, and in-ear, each providing comfort, noise isolation, and superior sound clarity. Features like wireless connectivity, active noise cancellation, built-in microphones, and long-lasting battery life enhance convenience and audio performance. Whether for professional use, entertainment, or travel, headphones combine style, comfort, and exceptional sound fidelity to bring your audio experience to life."),
        new ItemCard(R.drawable.headphone3, "$1234", "Sony", "m-123","Black", "Headphones are audio devices designed to deliver immersive sound directly to your ears, offering a personal and high-quality listening experience. They are ideal for music, gaming, calls, podcasts, and multimedia consumption. Modern headphones come in various types, including over-ear, on-ear, and in-ear, each providing comfort, noise isolation, and superior sound clarity. Features like wireless connectivity, active noise cancellation, built-in microphones, and long-lasting battery life enhance convenience and audio performance. Whether for professional use, entertainment, or travel, headphones combine style, comfort, and exceptional sound fidelity to bring your audio experience to life."),
        new ItemCard(R.drawable.headphone4, "$1234", "Sony", "m-123","Black", "Headphones are audio devices designed to deliver immersive sound directly to your ears, offering a personal and high-quality listening experience. They are ideal for music, gaming, calls, podcasts, and multimedia consumption. Modern headphones come in various types, including over-ear, on-ear, and in-ear, each providing comfort, noise isolation, and superior sound clarity. Features like wireless connectivity, active noise cancellation, built-in microphones, and long-lasting battery life enhance convenience and audio performance. Whether for professional use, entertainment, or travel, headphones combine style, comfort, and exceptional sound fidelity to bring your audio experience to life.")
    ));

}
