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
    static public final String dodPrice = "$999.99";
    static public final String dodName = "Microphone";
    static public final String dodDescription = "A microphone is an essential audio device that captures sound waves and converts them into electrical signals for recording, amplification, or broadcasting. It allows for clear voice communication, high-quality audio recording, and seamless sound transmission in a variety of settings—whether in professional studios, live performances, podcasts, gaming, or virtual meetings. Modern microphones come in various types, including dynamic, condenser, and USB models, each designed to suit different audio needs. With features like noise cancellation, cardioid pickup patterns, and plug-and-play functionality, microphones ensure crisp, accurate, and reliable sound capture for both amateurs and professionals alike.";
    static public final String dodModel = "123-A";
    static public final String dodColor = "Black";

    static public ArrayList<ItemCard> items = new ArrayList<>(Arrays.asList(
        new ItemCard(R.drawable.headphone1, "$199.99", "JBL Headset", "J-123","Off-White", "Experience immersive sound like never before with the JBL Headset. Designed for comfort and long-lasting use, this headset delivers crystal-clear audio and deep bass, perfect for gaming, music, and calls. Its lightweight, ergonomic design ensures a snug fit, while the built-in microphone lets you communicate effortlessly. Enjoy seamless connectivity, durable build quality, and the signature JBL sound that brings every note to life."),
        new ItemCard(R.drawable.headphone2, "$299.99", "Audionic Headset", "A-123","White", "Experience superior audio with the Audionic Headset, crafted for comfort and clarity. Perfect for music, gaming, and calls, it delivers crisp sound and rich bass. Its lightweight design ensures hours of comfortable use, while the built-in microphone makes communication effortless. Durable, stylish, and reliable, the Audionic headset brings your audio to life."),
        new ItemCard(R.drawable.keyboard1, "$49.99", "HP Keyboard", "H-123","Black", "Enhance your typing experience with the HP Keyboard. Designed for comfort and precision, it delivers responsive keys, smooth performance, and reliable durability for work, gaming, or everyday use. Its sleek design and ergonomic layout make typing effortless, while its sturdy build ensures long-lasting performance."),
        new ItemCard(R.drawable.headphone3, "$399.99", "Ronin Headset", "R-123","Purple", "Dive into crystal-clear sound with the Ronin Headset. Engineered for comfort and performance, it delivers powerful bass, crisp highs, and immersive audio for gaming, music, and calls. Its sleek design, durable build, and built-in microphone make it the perfect companion for long listening sessions."),
        new ItemCard(R.drawable.pc3, "$599.99", "Acer Gaming PC", "Ac-123","Glass", "Experience high-performance gaming with the Acer Gaming PC. Powered by an Intel Core i7-12700KF processor, 16GB DDR4 RAM, and an NVIDIA GeForce RTX 4060 GPU, it delivers smooth gameplay and stunning graphics. Enjoy fast storage with a 1TB NVMe SSD and reliable power from a 700W PSU. Its sleek, durable chassis and efficient cooling system make it perfect for long gaming sessions."),
        new ItemCard(R.drawable.headphone4, "$499.99", "JBL Headset", "J-567","Black", "Immerse yourself in rich, powerful sound with the JBL Headset. Designed for comfort and style, it delivers deep bass, clear highs, and seamless audio for gaming, music, and calls. With a durable build and built-in microphone, it’s perfect for long listening sessions and effortless communication."),
        new ItemCard(R.drawable.pc1, "$999.99", "Asus Gaming PC", "As-123","White", "Unleash next-level performance with the ASUS Gaming PC. Powered by an Intel Core i7-12700K processor, 16GB DDR4 RAM, and an NVIDIA GeForce RTX 4070 GPU, it delivers smooth gameplay, stunning visuals, and lightning-fast multitasking. Enjoy a 1TB NVMe SSD for ultra-fast storage and a 750W power supply for reliable performance. Designed for gamers, its sleek, durable chassis and optimized cooling system ensure peak performance during intense gaming sessions."),
        new ItemCard(R.drawable.keyboard2, "$99.99", "Redragon Gaming Keyboard", "R-123","RCG", "Level up your gaming with the Redragon Gaming Keyboard. Featuring responsive mechanical keys, customizable RGB lighting, and a durable design, it delivers precision and performance for every battle. Ergonomic and stylish, this keyboard ensures comfort during long gaming sessions while giving your setup a striking look."),
        new ItemCard(R.drawable.pc2, "$1999.99", "MSI Gaming PC", "M-123","Black", "Dominate every game with the MSI Gaming PC. Equipped with an Intel Core i9-12900K processor, 32GB DDR5 RAM, and an NVIDIA GeForce RTX 4080 GPU, it delivers ultra-smooth gameplay and breathtaking visuals. Store all your games and files on a 2TB NVMe SSD while a 850W power supply ensures stable performance. With a sleek, gamer-focused design and advanced cooling system, the MSI Gaming PC keeps you in the action for hours without compromise.")

    ));

}
