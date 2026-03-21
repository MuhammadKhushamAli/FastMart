package com.example.fastmart;

import android.app.Activity;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class login extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public login() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaterialButton loginButton = view.findViewById(R.id.loginButton);
        TextInputEditText emailField = view.findViewById(R.id.loginEmail);
        TextInputEditText passwordField = view.findViewById(R.id.loginPassword);
        Activity activity = requireActivity();

        loginButton.setOnClickListener((v) -> {
           try {
               String email = emailField.getText().toString();
               String password = passwordField.getText().toString();

               if (email.isEmpty() || password.isEmpty()) {
                   Toast.makeText(activity, "All Fields Required", Toast.LENGTH_LONG).show();
                   return;
               }

               SharedPreferences sPref = activity.getSharedPreferences(KeyUtils.userFileKey, activity.MODE_PRIVATE);

               if ((sPref.getString(KeyUtils.emailPrefKey + email, "").equals(email)) &&
                       (sPref.getString(KeyUtils.passPrefKey + email, "").equals(password))) {
                   if (!(sPref.edit().putBoolean(KeyUtils.isLoggedInKey, true).commit())) {
                       Toast.makeText(activity, "Unable To LoggedIn", Toast.LENGTH_LONG).show();
                   }
               }
               else {
                   Toast.makeText(activity, "Incorrect Details", Toast.LENGTH_LONG).show();
               }
           } catch (Exception e) {
               Toast.makeText(activity, "Something Went Wrong", Toast.LENGTH_LONG).show();
           }
        });
    }
}