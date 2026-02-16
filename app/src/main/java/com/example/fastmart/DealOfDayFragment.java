package com.example.fastmart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class DealOfDayFragment extends Fragment {

    ImageView dodImage;
    TextView dodCategory;
    TextView dodName;
    TextView dodPrice;
    TextView dodDescription;


    private static final String ARG_IMAGE_KEY = "image_key";
    private static final String ARG_CATEGORY = "item_category";
    private static final String ARG_NAME = "item_name";
    private static final String ARG_PRICE = "item_price";
    private static final String ARG_DESCRIPTION = "item_description";


    int paramImageKey;
    String paramCategory;
    String paramName;
    String paramPrice;
    String paramDescription;

    public static DealOfDayFragment newInstance(int imageKey, String category, String name, String price, String description) {
        DealOfDayFragment fragment = new DealOfDayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_KEY, imageKey);
        args.putString(ARG_CATEGORY, category);
        args.putString(ARG_NAME, name);
        args.putString(ARG_PRICE, price);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    public DealOfDayFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paramImageKey = getArguments().getInt(ARG_IMAGE_KEY);
            paramCategory = getArguments().getString(ARG_CATEGORY);
            paramName = getArguments().getString(ARG_NAME);
            paramPrice = getArguments().getString(ARG_PRICE);
            paramDescription = getArguments().getString(ARG_DESCRIPTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deal_of_day, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dodImage = view.findViewById(R.id.deal_of_the_day_image);
        dodCategory = view.findViewById(R.id.dod_item_catagory);
        dodPrice = view.findViewById(R.id.dod_item_price);
        dodName = view.findViewById(R.id.dod_item_name);
        dodDescription = view.findViewById(R.id.dod_item_description);

        dodImage.setImageResource(paramImageKey);
        dodCategory.setText(paramCategory);
        dodPrice.setText(paramPrice);
        dodName.setText(paramName);
        dodDescription.setText(paramDescription);
    }
}