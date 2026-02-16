package com.example.fastmart;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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

    BuyFragment buyFragment;
    private StringBuilder stringBuilder;

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
        stringBuilder = new StringBuilder();
        stringBuilder.append("You are going to buy ").append(itemName).append(" in ").append(itemColor)
                .append(" color for ").append(itemPrice);

        buyFragment = BuyFragment.newInstance(stringBuilder.toString());

        fragManager.beginTransaction()
                .add(R.id.confirmation_popup, buyFragment)
                .hide(buyFragment)
                .commit();

        buyButton.setOnClickListener((v) -> {
            fragManager.beginTransaction()
                    .show(buyFragment)
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
    public void onBuyClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.SEND_SMS}, 1
            );
        }
        else {
            String message = "You bought" + stringBuilder.substring(20);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(DataFile.phoneNumber, null, message, null, null);

            fragManager.beginTransaction()
                    .hide(buyFragment)
                    .commit();
        }
    }

    @Override
    public void onCancelClick() {
        fragManager.beginTransaction()
                .hide(buyFragment)
                .commit();
    }
}