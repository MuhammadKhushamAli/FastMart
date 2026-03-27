package com.example.fastmart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = requireContext();
        TextInputEditText fullNameField = view.findViewById(R.id.profile_fullName);
        TextInputEditText emailField = view.findViewById(R.id.profile_email);
        TextInputEditText dobField = view.findViewById(R.id.profile_dob);
        TextInputEditText genderField = view.findViewById(R.id.profile_gender);
        TextInputEditText phNoField = view.findViewById(R.id.profile_phNo);
        MaterialButton logoutBtn = view.findViewById(R.id.logout_btn);

        MyApplication app = (MyApplication) context.getApplicationContext();
        SharedPreferences sPref = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);

        fullNameField.setInputType(InputType.TYPE_NULL);
        emailField.setInputType(InputType.TYPE_NULL);
        dobField.setInputType(InputType.TYPE_NULL);
        genderField.setInputType(InputType.TYPE_NULL);
        phNoField.setInputType(InputType.TYPE_NULL);

        fullNameField.setText(app.name);
        emailField.setText(app.email);
        dobField.setText(app.dob);
        genderField.setText(app.gender);
        phNoField.setText(app.phNo);

        logoutBtn.setOnClickListener(v -> {
            sPref.edit().putBoolean(KeyUtils.isLoggedInKey, false)
                    .commit();
            Intent intent = new Intent(context, SplashScreenActivity.class);
            startActivity(intent);
        });
    }
}