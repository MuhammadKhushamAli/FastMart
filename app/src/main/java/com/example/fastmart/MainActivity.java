package com.example.fastmart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ItemsListFragment.onItemClicked {

    FragmentManager fragManager;
    View dodFrag;

    // Dod Segment
    ImageView dodImage;
    TextView dodCategory;
    TextView dodPrice;
    TextView dodName;
    TextView dodDescription;

    // Item List
    ArrayList<CardView> itemCardsList;
    View listFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fragManager = getSupportFragmentManager();

        // Deal of the Day Segment
        setDodContent();


        fragManager.beginTransaction()
                .show(fragManager.findFragmentById(R.id.home_item_list))
                .commit();

    }

    @Override
    public void onItemClickListener(int index) {

    }

    private void setDodContent() {
        dodFrag = fragManager.findFragmentById(R.id.dod_banner).requireView();
        dodImage = dodFrag.findViewById(R.id.deal_of_the_day_image);
        dodCategory = dodFrag.findViewById(R.id.dod_item_catagory);
        dodPrice = dodFrag.findViewById(R.id.dod_item_price);
        dodName = dodFrag.findViewById(R.id.dod_item_name);
        dodDescription = dodFrag.findViewById(R.id.dod_item_description);

        dodImage.setImageResource(R.drawable.dod);
        dodCategory.setText(DataFile.dodCatagory);
        dodPrice.setText(DataFile.dodPrice);
        dodName.setText(DataFile.dodName);
        dodDescription.setText(DataFile.dodDescription);
    }
}