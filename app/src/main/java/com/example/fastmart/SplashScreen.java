package com.example.fastmart;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    Animation top_to_current;
    Animation bottom_to_current;
    ImageView logo;
    TextView slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        applyAnimation();
    }

    private void init()
    {
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        top_to_current = AnimationUtils.loadAnimation(this, R.anim.top_to_current);
        bottom_to_current = AnimationUtils.loadAnimation(this, R.anim.bottom_to_current);

    }

    private void applyAnimation()
    {
        logo.setAnimation(top_to_current);
        slogan.setAnimation(bottom_to_current);
    }
}