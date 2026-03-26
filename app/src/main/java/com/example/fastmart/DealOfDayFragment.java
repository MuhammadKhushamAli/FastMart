package com.example.fastmart;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DealOfDayFragment extends Fragment
        implements CarouselViewPagerAdapter.setOnCLickListener{

    MyApplication app;
    CarouselViewPagerAdapter carouselViewPagerAdapter;
    setOnClickListener parentActivity;
    ViewPager2 viewPager2Carousel;

    public DealOfDayFragment() {
        // Required empty public constructor
    }

    public interface setOnClickListener {
        public void addToFav(Item item);
        public void removeFromFav(Item item);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        parentActivity = (setOnClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deal_of_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();

        app = (MyApplication) context.getApplicationContext();

        viewPager2Carousel = view.findViewById(R.id.carouselViewPager2);

        carouselViewPagerAdapter = new CarouselViewPagerAdapter(context, app.dodItems, this);
        viewPager2Carousel.setAdapter(carouselViewPagerAdapter);
    }

    @Override
    public void addToFav(Item item) {
        parentActivity.addToFav(item);
    }

    @Override
    public void removeFromFav(Item item) {
        parentActivity.removeFromFav(item);
    }

    public void notifyItemChanged(Item item) {
        viewPager2Carousel.setAdapter(null);
        viewPager2Carousel.setAdapter(carouselViewPagerAdapter);
    }
}