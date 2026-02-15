package com.example.fastmart;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    Animation top_to_current;
    Animation bottom_to_current;
    Animation loading_bar_anim;
    ImageView logo;
    TextView slogan;
    View loading_bar;
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

        new Handler().postDelayed(() -> moveToHome(), 5000);
    }

    private void init()
    {
        logo = findViewById(R.id.logo);
        slogan = findViewById(R.id.slogan);
        loading_bar = findViewById(R.id.loading_bar);
        loading_bar.setVisibility(View.GONE);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) loading_bar.getLayoutParams();
            params.setMargins(0, 100, 0, 0);
            loading_bar.setLayoutParams(params);
        }

        top_to_current = AnimationUtils.loadAnimation(this, R.anim.top_to_current);
        bottom_to_current = AnimationUtils.loadAnimation(this, R.anim.bottom_to_current);
        loading_bar_anim = AnimationUtils.loadAnimation(this, R.anim.loading_bar);
    }

    private void applyAnimation()
    {
        logo.setAnimation(top_to_current);
        slogan.setAnimation(bottom_to_current);
        loading_bar.postDelayed(() -> {
                loading_bar.setVisibility(View.VISIBLE);
                loading_bar.setAnimation(loading_bar_anim);
        }, 3000);
    }

    private void moveToHome()
    {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }
}