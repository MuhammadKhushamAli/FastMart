package com.example.fastmart;

import android.app.Activity;
import android.content.Context;
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

    public signup() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Context context = requireContext();
            try {
                String password = passwordField.getText().toString();
                String verifyPassword = verifyPasswordField.getText().toString();
                String email = emailField.getText().toString();

                if (email.isEmpty() || password.isEmpty() || verifyPassword.isEmpty()) {
                    Toast.makeText(context, "All Fields Required", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!password.equals(verifyPassword))
                {
                    Toast.makeText(context, "Password and Verify Password Must be Equal", Toast.LENGTH_LONG).show();
                    return;
                }

                SharedPreferences sPref = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);
                SharedPreferences.Editor sPrefEditor = sPref.edit();
                sPrefEditor.putString(KeyUtils.emailPrefKey + email, email);
                sPrefEditor.putString(KeyUtils.passPrefKey + email, password);
                if (sPrefEditor.commit()) {
                    sPrefEditor.putBoolean(KeyUtils.isLoggedInKey, true)
                            .apply();
                    startActivity(new Intent(context, MainActivity.class));
                    context.finish();
                }
                else {
                    Toast.makeText(context, "Unable to Save Details", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                Toast.makeText(context,"Something Went Wrong", Toast.LENGTH_LONG).show();
            }

        });
    }
}