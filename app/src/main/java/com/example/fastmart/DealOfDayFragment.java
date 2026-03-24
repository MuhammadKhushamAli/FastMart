package com.example.fastmart;

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

public class DealOfDayFragment extends Fragment {
    public DealOfDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
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

        MyApplication app = (MyApplication) context.getApplicationContext();

        ViewPager2 viewPager2Carousel = view.findViewById(R.id.carouselViewPager2);
        CarouselViewPagerAdapter carouselViewPagerAdapter = new CarouselViewPagerAdapter(context, app.dodItems);

        viewPager2Carousel.setAdapter(carouselViewPagerAdapter);
    }
}