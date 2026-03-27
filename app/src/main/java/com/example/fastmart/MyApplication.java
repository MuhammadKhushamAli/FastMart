package com.example.fastmart;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;

public class MyApplication extends Application {
    public ArrayList<Item> items;
    public ArrayList<Item> dodItems;
    public ArrayList<Item> cart;
    public ArrayList<Item> wishlist;

    public String name;
    public String email;
    public String phNo;
    public String dob;
    public String gender;
    public final String appPhNo = "03107488488";

    @Override
    public void onCreate() {
        super.onCreate();
        items = new ArrayList<>(Arrays.asList(
                new Item(1,
                        R.drawable.headphone1,
                        199.99f, "JBL Headset",
                        "J-123",
                        "Off-White",
                        "Experience immersive sound like never before with the JBL Headset. Designed for comfort and long-lasting use, this headset delivers crystal-clear audio and deep bass, perfect for gaming, music, and calls. Its lightweight, ergonomic design ensures a snug fit, while the built-in microphone lets you communicate effortlessly. Enjoy seamless connectivity, durable build quality, and the signature JBL sound that brings every note to life.",
                        "Headset",
                        10
                ),
                new Item(2,
                        R.drawable.headphone2,
                        299.99f,
                        "Audionic Headset",
                        "A-123",
                        "White",
                        "Experience superior audio with the Audionic Headset, crafted for comfort and clarity. Perfect for music, gaming, and calls, it delivers crisp sound and rich bass. Its lightweight design ensures hours of comfortable use, while the built-in microphone makes communication effortless. Durable, stylish, and reliable, the Audionic headset brings your audio to life.",
                        "Headset",
                        20
                ),
                new Item(3,
                        R.drawable.keyboard1,
                        49.99f,
                        "HP Keyboard",
                        "H-123",
                        "Black",
                        "Enhance your typing experience with the HP Keyboard. Designed for comfort and precision, it delivers responsive keys, smooth performance, and reliable durability for work, gaming, or everyday use. Its sleek design and ergonomic layout make typing effortless, while its sturdy build ensures long-lasting performance.",
                        "Keyboard",
                        30
                ),
                new Item(4,
                        R.drawable.headphone3,
                        399.99f,
                        "Ronin Headset",
                        "R-123",
                        "Purple",
                        "Dive into crystal-clear sound with the Ronin Headset. Engineered for comfort and performance, it delivers powerful bass, crisp highs, and immersive audio for gaming, music, and calls. Its sleek design, durable build, and built-in microphone make it the perfect companion for long listening sessions.",
                        "Headset",
                        10
                ),
                new Item(5,
                        R.drawable.pc3,
                        599.99f,
                        "Acer Gaming PC",
                        "Ac-123",
                        "Glass",
                        "Experience high-performance gaming with the Acer Gaming PC. Powered by an Intel Core i7-12700KF processor, 16GB DDR4 RAM, and an NVIDIA GeForce RTX 4060 GPU, it delivers smooth gameplay and stunning graphics. Enjoy fast storage with a 1TB NVMe SSD and reliable power from a 700W PSU. Its sleek, durable chassis and efficient cooling system make it perfect for long gaming sessions.",
                        "PC",
                        5
                ),
                new Item(6,
                        R.drawable.headphone4,
                        499.99f,
                        "JBL Headset",
                        "J-567",
                        "Black",
                        "Immerse yourself in rich, powerful sound with the JBL Headset. Designed for comfort and style, it delivers deep bass, clear highs, and seamless audio for gaming, music, and calls. With a durable build and built-in microphone, it’s perfect for long listening sessions and effortless communication.",
                        "Headset",
                        60
                ),
                new Item(7,
                        R.drawable.pc1,
                        999.99f,
                        "Asus Gaming PC",
                        "As-123",
                        "White",
                        "Unleash next-level performance with the ASUS Gaming PC. Powered by an Intel Core i7-12700K processor, 16GB DDR4 RAM, and an NVIDIA GeForce RTX 4070 GPU, it delivers smooth gameplay, stunning visuals, and lightning-fast multitasking. Enjoy a 1TB NVMe SSD for ultra-fast storage and a 750W power supply for reliable performance. Designed for gamers, its sleek, durable chassis and optimized cooling system ensure peak performance during intense gaming sessions.",
                        "PC",
                        30
                ),
                new Item(8,
                        R.drawable.keyboard2,
                        99.99f,
                        "Redragon Gaming Keyboard",
                        "R-123",
                        "RCG",
                        "Level up your gaming with the Redragon Gaming Keyboard. Featuring responsive mechanical keys, customizable RGB lighting, and a durable design, it delivers precision and performance for every battle. Ergonomic and stylish, this keyboard ensures comfort during long gaming sessions while giving your setup a striking look.",
                        "Keyboard",
                        7
                ),
                new Item(9,
                        R.drawable.pc2,
                        1999.99f,
                        "MSI Gaming PC",
                        "M-123",
                        "Black",
                        "Dominate every game with the MSI Gaming PC. Equipped with an Intel Core i9-12900K processor, 32GB DDR5 RAM, and an NVIDIA GeForce RTX 4080 GPU, it delivers ultra-smooth gameplay and breathtaking visuals. Store all your games and files on a 2TB NVMe SSD while a 850W power supply ensures stable performance. With a sleek, gamer-focused design and advanced cooling system, the MSI Gaming PC keeps you in the action for hours without compromise.",
                        "PC",
                        8
                )
        ));
        dodItems = new ArrayList<>(Arrays.asList(
                new Item(10,
                        R.drawable.microphone,
                        999.99f,
                        "Microphone",
                        "123-A",
                        "Black",
                        "A microphone is an essential audio device that captures sound waves and converts them into electrical signals for recording, amplification, or broadcasting. It allows for clear voice communication, high-quality audio recording, and seamless sound transmission in a variety of settings—whether in professional studios, live performances, podcasts, gaming, or virtual meetings. Modern microphones come in various types, including dynamic, condenser, and USB models, each designed to suit different audio needs. With features like noise cancellation, cardioid pickup patterns, and plug-and-play functionality, microphones ensure crisp, accurate, and reliable sound capture for both amateurs and professionals alike.",
                        899.99f,
                        "Microphone",
                        70
                ),
                new Item(11,
                        R.drawable.pc2,
                        1999.99f,
                        "MSI Gaming PC",
                        "M-123",
                        "Black",
                        "Dominate every game with the MSI Gaming PC. Equipped with an Intel Core i9-12900K processor, 32GB DDR5 RAM, and an NVIDIA GeForce RTX 4080 GPU, it delivers ultra-smooth gameplay and breathtaking visuals. Store all your games and files on a 2TB NVMe SSD while a 850W power supply ensures stable performance. With a sleek, gamer-focused design and advanced cooling system, the MSI Gaming PC keeps you in the action for hours without compromise.",
                        999.99f,
                        "PC",
                        50
                )
        ));
        cart = new ArrayList<>();
        wishlist = new ArrayList<>();
    }
}