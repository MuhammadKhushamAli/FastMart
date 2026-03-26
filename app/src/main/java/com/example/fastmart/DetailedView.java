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

public class DetailedView extends AppCompatActivity implements BuyAlertFragment.OnClickListener {
    ImageView detailedImage;
    ImageView backArrow;
    TextView name;
    TextView price;
    TextView description;
    TextView model;
    TextView color;
    Button buyButton;

    FragmentManager fragManager;

    BuyAlertFragment buyFragment;
    private StringBuilder stringBuilder;

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

        buyFragment = BuyAlertFragment.newInstance(stringBuilder.toString());

        fragManager.beginTransaction()
                .add(R.id.confirmation_popup, buyFragment)
                .hide(buyFragment)
                .commit();

        buyButton.setOnClickListener((v) -> {
//            fragManager.beginTransaction()
//                    .show(buyFragment)
//                    .commit();

        });
        backArrow.setOnClickListener((v) -> {
            finish();
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
        backArrow = findViewById(R.id.back_arrow);
        app = (MyApplication) getApplicationContext();

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
            SmsManager smsManager = SmsManager.getDefault();
            SharedPreferences sPref = getSharedPreferences(KeyUtils.userFileKey, MODE_PRIVATE);

            if(sPref.getBoolean(KeyUtils.isLoggedInKey, false)) {
                String userPhoneNumber = app.phNo;
                String appPhNo = app.appPhNo;
                String name = app.name;
                if(!(userPhoneNumber.isEmpty() || name.isEmpty())) {
                    String toUserMessage = "You bought" + stringBuilder.substring(20);
                    String toAppMessage = name + " bought" + stringBuilder.substring(20);
                    smsManager.sendTextMessage(userPhoneNumber, null, toUserMessage, null, null);
                    smsManager.sendTextMessage(appPhNo, null, toAppMessage, null, null);
                }
                else {
                    Toast.makeText(this, "First Complete Your Personal Details", Toast.LENGTH_LONG).show();
                    return;
                }

            }
            else {
                startActivity(new Intent(DetailedView.this, LoginSignupActivity.class));
                finish();
            }
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