package com.example.fastmart;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LoginViewPagerAdapter extends FragmentStateAdapter {
    public LoginViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new login();
            case 1:
                return new signup();
            default:
                return new login();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
