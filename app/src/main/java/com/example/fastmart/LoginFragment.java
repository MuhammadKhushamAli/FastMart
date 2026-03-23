package com.example.fastmart;

import android.content.Context;
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

public class LoginFragment extends Fragment {
    public LoginFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Context context = requireContext();

        loginButton.setOnClickListener((v) -> {
           try {
               String email = emailField.getText().toString();
               String password = passwordField.getText().toString();

               if (email.isEmpty() || password.isEmpty()) {
                   Toast.makeText(context, "All Fields Required", Toast.LENGTH_LONG).show();
                   return;
               }

               SharedPreferences sPref = context.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);

               if (sPref.getString(KeyUtils.emailPrefKey, "").isEmpty())
               {
                   Toast.makeText(context, "User Not Found", Toast.LENGTH_LONG).show();
                   return;
               }

               if ((sPref.getString(KeyUtils.emailPrefKey, "").equals(email)) &&
                       (sPref.getString(KeyUtils.passPrefKey + email, "").equals(password))) {
                   if (!(sPref.edit().putBoolean(KeyUtils.isLoggedInKey, true).commit())) {
                       Toast.makeText(context, "Unable To LoggedIn", Toast.LENGTH_LONG).show();
                   }
               }
               else {
                   Toast.makeText(context, "Incorrect Details", Toast.LENGTH_LONG).show();
               }
           } catch (Exception e) {
               Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show();
           }
        });
    }
}