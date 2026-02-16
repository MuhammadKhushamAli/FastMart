package com.example.fastmart;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ItemsListFragment.onItemClicked, DealOfDayFragment.onClickListener {

    FragmentManager fragManager;
    View dodFrag;

    // Dod Segment


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
        DealOfDayFragment dealOfDayFragment = DealOfDayFragment.newInstance(
                R.drawable.dod,
                DataFile.dodCatagory,
                DataFile.dodName,
                DataFile.dodPrice,
                DataFile.dodDescription
        );

        fragManager.beginTransaction()
                .replace(R.id.dod_banner, dealOfDayFragment)
                .show(fragManager.findFragmentById(R.id.home_item_list))
                .commit();

    }

    @Override
    public void onItemClickListener(int index) {
        DataFile.ItemCard card = DataFile.items.get(index);
        Intent i = new Intent(MainActivity.this, DetailedView.class);
        i.putExtra(KeyUtils.imageIDKey, card.imageID);
        i.putExtra(KeyUtils.nameKey, card.name);
        i.putExtra(KeyUtils.priceKey, card.price);
        i.putExtra(KeyUtils.descriptionKey, card.description);
        i.putExtra(KeyUtils.modelKey, card.model);
        i.putExtra(KeyUtils.colorKey, card.color);

        startActivity(i);
    }

    @Override
    public void onItemClick(View view) {
        ImageView image = view.findViewById(R.id.deal_of_the_day_image);
        int imageKey = (int) image.getTag();
        TextView name = view.findViewById(R.id.dod_item_name);
        TextView price = view.findViewById(R.id.dod_item_price);
        TextView description = view.findViewById(R.id.dod_item_description);

        Intent i = new Intent(MainActivity.this, DetailedView.class);

        i.putExtra(KeyUtils.imageIDKey,imageKey);
        i.putExtra(KeyUtils.nameKey, name.getText());
        i.putExtra(KeyUtils.priceKey, price.getText());
        i.putExtra(KeyUtils.descriptionKey, description.getText());
        i.putExtra(KeyUtils.modelKey, DataFile.dodModel);
        i.putExtra(KeyUtils.colorKey, DataFile.dodColor);

        startActivity(i);
    }
}