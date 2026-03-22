package com.example.fastmart;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
        TextInputEditText nameField = view.findViewById(R.id.signUpFullName);
        TextInputEditText dobField = view.findViewById(R.id.signUpDOB);
        TextInputEditText phNoField = view.findViewById(R.id.signUpPhNo);
        TextInputEditText emailField = view.findViewById(R.id.signUpEmail);
        TextInputEditText passwordField = view.findViewById(R.id.signUpPassword);
        TextInputEditText verifyPasswordField = view.findViewById(R.id.signUpVerifyPassword);

        AutoCompleteTextView genderField = view.findViewById(R.id.signUpGender);


        genderFieldCreator(R.id.signUpGender);

        dateDialogueCreator(R.id.signUpDOB);

        signupButton.setOnClickListener((v) -> {
            validateAndSave(nameField, dobField, genderField, emailField, phNoField, passwordField, verifyPasswordField);
        });
    }
    private void dateDialogueCreator(int idOfField) {
        TextInputEditText dobField = view.findViewById(idOfField);
        // Calender
        dobField.setInputType(InputType.TYPE_NULL);
        dobField.setOnClickListener((v) -> {
            final Calendar calendar = Calendar.getInstance();
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireActivity(), (dobView, pickedYear, pickedMonth, pickedDay) -> {
                dobField.setText(pickedDay + "/" + pickedMonth + "/" + pickedYear);
            }, currentYear, currentMonth, currentDay);
            datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });
    }

    private void genderFieldCreator(int idOfField) {

        // Gender Dropdown List
        genderField.setKeyListener(null);
        final String[] genders = {"Male", "Female", "Other"};
        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                genders
        );
        genderField.setAdapter(adapterGender);
    }

    private void validateAndSave(
            TextInputEditText nameField,
            TextInputEditText dobField,
            AutoCompleteTextView genderField,
            TextInputEditText emailField,
            TextInputEditText phNoField,
            TextInputEditText passwordField,
            TextInputEditText verifyPasswordField
    ) {
        Context context = requireContext();
        Activity activity = requireActivity();
        try {
            String name = nameField.getText().toString();
            String dob = dobField.getText().toString();
            String gender = genderField.getText().toString();
            String email = emailField.getText().toString();
            String phNo = phNoField.getText().toString();
            String password = passwordField.getText().toString();
            String verifyPassword = verifyPasswordField.getText().toString();


            if (
                    name.isEmpty() || email.isEmpty() ||
                    phNo.isEmpty() || password.isEmpty() ||
                    verifyPassword.isEmpty() || dob.isEmpty() ||
                            gender.isEmpty()
            ) {
                Toast.makeText(context, "All Fields Required", Toast.LENGTH_LONG).show();
                return;
            }

            if (!password.equals(verifyPassword)) {
                Toast.makeText(context, "Password and Verify Password Must be Equal", Toast.LENGTH_LONG).show();
                return;
            }

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate parsedDate = LocalDate.parse(dob, formatter);

            if (parsedDate.isAfter(currentDate)) {
                Toast.makeText(context, "Date of Birth Must be Correct", Toast.LENGTH_LONG).show();
                return;
            }

            // Saving Data
            SharedPreferences sPref = activity.getSharedPreferences(KeyUtils.userFileKey, Context.MODE_PRIVATE);
            SharedPreferences.Editor sPrefEditor = sPref.edit();

            sPrefEditor.putString(KeyUtils.namePrefKey + email, name);
            sPrefEditor.putString(KeyUtils.dobPrefKey + email, dob);
            sPrefEditor.putString(KeyUtils.genderPrefKey + email, gender);
            sPrefEditor.putString(KeyUtils.phNoPrefKey + email, phNo);
            sPrefEditor.putString(KeyUtils.emailPrefKey + email, email);
            sPrefEditor.putString(KeyUtils.passPrefKey + email, password);

            if (sPrefEditor.commit()) {
                sPrefEditor.putBoolean(KeyUtils.isLoggedInKey, true)
                        .apply();
                startActivity(new Intent(activity, MainActivity.class));
                activity.finish();
            }
            else {
                Toast.makeText(context, "Unable to Save Details", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context,"Something Went Wrong", Toast.LENGTH_LONG).show();
        }
    }
}