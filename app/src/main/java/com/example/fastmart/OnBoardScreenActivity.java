package com.example.fastmart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class OnBoardScreenActivity extends AppCompatActivity {
    MaterialButton button;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_board_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        button.setOnClickListener((v) -> {
            startActivity(new Intent(OnBoardScreenActivity.this, LoginSignupActivity.class));
            finish();
        });
    }

    private void init() {
        button = findViewById(R.id.onBoardButton);
        sPref = getSharedPreferences(KeyUtils.userFileKey, MODE_PRIVATE);
        if (sPref.getBoolean(KeyUtils.isFirstTimeApp, true))
        {
            sPref.edit().putBoolean(KeyUtils.isFirstTimeApp, false).apply();
        }
    }
}