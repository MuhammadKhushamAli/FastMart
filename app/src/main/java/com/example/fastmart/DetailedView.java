package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailedView extends AppCompatActivity {
    ImageView detailedImage;
    TextView name;
    TextView price;
    TextView description;
    TextView model;
    TextView color;

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
        Intent i = getIntent();

        int imageID = i.getIntExtra(KeyUtils.imageIDKey, 0);
        String name = i.getStringExtra(KeyUtils.nameKey);
        String price = i.getStringExtra(KeyUtils.priceKey);
        String desc = i.getStringExtra(KeyUtils.descriptionKey);
        String model = i.getStringExtra(KeyUtils.modelKey);
        String color = i.getStringExtra(KeyUtils.colorKey);

        initAndSetup(
                imageID,
                name,
                price,
                desc,
                model,
                color
        );

    }

    private void initAndSetup(int imgID, String itemName, String itemPrice, String itemDesc, String itemModel, String itemColor)
    {
        detailedImage = findViewById(R.id.detailed_item_image);
        name = findViewById(R.id.detailed_item_name);
        price = findViewById(R.id.detailed_item_price);
        description = findViewById(R.id.detailed_item_description);
        model = findViewById(R.id.detailed_item_model);
        color = findViewById(R.id.detailed_item_color);

        detailedImage.setImageResource(imgID);
        name.setText(itemName);
        price.setText(itemPrice);
        description.setText(itemDesc);
        model.setText(itemModel);
        color.setText(itemColor);
    }
}