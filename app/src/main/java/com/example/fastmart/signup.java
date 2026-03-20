package com.example.fastmart;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class signup extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public signup() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton signupButton = view.findViewById(R.id.signUpButton);
        TextInputEditText emailField = view.findViewById(R.id.signUpEmail);
        TextInputEditText passwordField = view.findViewById(R.id.signUpPassword);
        TextInputEditText verifyPasswordField = view.findViewById(R.id.signUpVerifyPassword);

        signupButton.setOnClickListener((v) -> {
            Activity activity = requireActivity();
            try {
                String password = passwordField.getText().toString();
                String verifyPassword = verifyPasswordField.getText().toString();
                String email = emailField.getText().toString();

                if (email.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()) {
                    Toast.makeText(activity, "All Fields Required", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(verifyPassword))
                {
                    Toast.makeText(activity, "Password and Verify Password Must be Equal", Toast.LENGTH_LONG).show();
                    return;
                }

                SharedPreferences sPref = activity.getSharedPreferences(KeyUtils.userFileKey, activity.MODE_PRIVATE);
                SharedPreferences.Editor sPrefEditor = sPref.edit();
                sPrefEditor.putString(KeyUtils.emailPrefKey + email, email);
                sPrefEditor.putString(KeyUtils.passPrefKey + email, password);
                if (sPrefEditor.commit()) {
                    sPrefEditor.putBoolean(KeyUtils.isLoggedInKey, true)
                            .apply();
                    startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                }
                else {
                    Toast.makeText(activity, "Unable to Save Details", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(activity,"Something Went Wrong", Toast.LENGTH_LONG).show();
            }

        });
    }
}