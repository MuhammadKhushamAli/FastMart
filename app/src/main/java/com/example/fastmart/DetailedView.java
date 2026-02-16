package com.example.fastmart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class DetailedView extends AppCompatActivity implements BuyFragment.OnClickListener {
    ImageView detailedImage;
    TextView name;
    TextView price;
    TextView description;
    TextView model;
    TextView color;
    Button buyButton;

    FragmentManager fragManager;

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
        String itemName = i.getStringExtra(KeyUtils.nameKey);
        String itemPrice = i.getStringExtra(KeyUtils.priceKey);
        String itemDesc = i.getStringExtra(KeyUtils.descriptionKey);
        String itemModel = i.getStringExtra(KeyUtils.modelKey);
        String itemColor = i.getStringExtra(KeyUtils.colorKey);

        initAndSetup(
                imageID,
                itemName,
                itemPrice,
                itemDesc,
                itemModel,
                itemColor
        );

        fragManager = getSupportFragmentManager();
        StringBuilder String = new StringBuilder();
        String.append("You are going to buy ").append(itemName).append(" in ").append(itemColor)
                .append(" color for ").append(itemPrice);

        BuyFragment buyFragment = BuyFragment.newInstance(String.toString());

        buyButton.setOnClickListener((v) -> {
            fragManager.beginTransaction()
                    .replace(R.id.confirmation_popup, buyFragment)
                    .commit();
        });
    }

    private void initAndSetup(
            int imageID,
            String itemName,
            String itemPrice,
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

        detailedImage.setImageResource(imageID);
        name.setText(itemName);
        price.setText(itemPrice);
        description.setText(itemDesc);
        model.setText(itemModel);
        color.setText(itemColor);
    }



    @Override
    public void onClick() {

    }

}