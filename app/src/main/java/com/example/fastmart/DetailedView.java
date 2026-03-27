package com.example.fastmart;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class DetailedView extends AppCompatActivity {
    ImageView detailedImage;
    ImageView backArrow;
    TextView name;
    TextView price;
    TextView description;
    TextView model;
    TextView color;
    Button buyButton;
    MyApplication app;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Item item = getIntent().getParcelableExtra(KeyUtils.itemKey);
        int id = item.getId();

        initAndSetup(
                item.getImageID(),
                item.getName(),
                item.getPrice(),
                item.getDescription(),
                item.getModel(),
                item.getColor()
        );


        buyButton.setOnClickListener((v) -> {
            Item itemToBeCarted = app.cart.stream().filter(itemInCart -> itemInCart.getId() == id)
                    .findFirst()
                    .orElse(null);

            if (itemToBeCarted == null) {
                app.cart.add(item);
            }
            else {
                itemToBeCarted.incItemsSelected();
            }
            Toast.makeText(this, item.getName() + " Added to Cart", Toast.LENGTH_LONG).show();
            finish();
        });
        backArrow.setOnClickListener((v) -> {
            finish();
        });
    }

    private void initAndSetup(
            int imageID,
            String itemName,
            Float itemPrice,
            String itemDesc,
            String itemModel,
            String itemColor
    )
    {
        detailedImage = findViewById(R.id.detailed_item_image);
        name = findViewById(R.id.detailed_item_name);
        price = findViewById(R.id.detailed_item_price);
        description = findViewById(R.id.detailed_item_description);
        model = findViewById(R.id.detailed_item_model);
        color = findViewById(R.id.detailed_item_color);
        buyButton = findViewById(R.id.buy_button);
        backArrow = findViewById(R.id.back_arrow);
        app = (MyApplication) getApplicationContext();

        detailedImage.setImageResource(imageID);
        name.setText(itemName);
        String priceStr = "$ " + itemPrice;
        price.setText(priceStr);
        description.setText(itemDesc);
        model.setText(itemModel);
        color.setText(itemColor);
    }
}