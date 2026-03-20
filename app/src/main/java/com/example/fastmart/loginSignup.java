package com.example.fastmart;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class loginSignup extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TabLayoutMediator mediator;
    LoginViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                viewPager2.post(() -> {
                    RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
                    View view = recyclerView.getLayoutManager().findViewByPosition(position);

                    if (view != null) {
                        view.post(() -> {
                            int measureX = View.MeasureSpec.makeMeasureSpec(viewPager2.getWidth(), View.MeasureSpec.EXACTLY);
                            int measureY = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            view.measure(measureX, measureY);
                            int height = view.getMeasuredHeight();

                            ViewGroup.LayoutParams params = viewPager2.getLayoutParams();
                            params.height = height;
                            viewPager2.setLayoutParams(params);
                        });
                    }
                });
            }
        });
    }
    private void init() {
        tabLayout = findViewById(R.id.loginSignupTabsLayout);
        viewPager2 = findViewById(R.id.loginSignupViewPager2);
        adapter = new LoginViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        mediator = new TabLayoutMediator(
                tabLayout,
                viewPager2,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                        switch (i) {
                            case 0:
                                tab.setText("Log In");
                                break;
                            case 1:
                                tab.setText("Sign Up");
                                break;

                        }
                    }
                }
        );
        mediator.attach();
    }
}