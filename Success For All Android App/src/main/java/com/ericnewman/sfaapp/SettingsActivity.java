package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.ericnewman.sfaapp.database.SQLiteHelper;
import com.ericnewman.sfaapp.databinding.ActivitySettingsBinding;
import com.ericnewman.sfaapp.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    ActivitySettingsBinding binding;
    UserDTO userDTO = new UserDTO();
    SQLiteHelper sqLiteHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Instantiate the SQLiteHelper
        sqLiteHelper = new SQLiteHelper(this);
        // Set up the age spinner
        List<Integer> ageList = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            ageList.add(i);
        }
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageList);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ageSpinner.setAdapter(ageAdapter);

        // Set up the number of children spinner
        List<Integer> childrenList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            childrenList.add(i);
        }
        ArrayAdapter<Integer> childrenAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, childrenList);
        childrenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.numberOfChildrenSpinner.setAdapter(childrenAdapter);
        binding.delete.setOnClickListener(v -> {
            sqLiteHelper.getAllData();
        });
        binding.submitButton.setOnClickListener(v -> {
            String firstName = binding.firstNameField.getText().toString();
            userDTO.setFirstName(firstName);
            String lastName = binding.lastNameField.getText().toString();
            userDTO.setLastName(lastName);
            int age = (int) binding.ageSpinner.getSelectedItem();
            userDTO.setAge(age);
            String streetAddress = binding.streetAddressField.getText().toString();
            userDTO.setUserStreet(streetAddress);
            String phone = binding.phoneField.getText().toString();
            userDTO.setPhone(phone);
            String state = binding.stateField.getText().toString();
            userDTO.setUserState(state);
            String zipCode = binding.zipCodeField.getText().toString();
            userDTO.setUserZip(zipCode);
            String country = binding.countryField.getText().toString();
            userDTO.setUserCountry(country);
            int numberOfChildren = (int) binding.numberOfChildrenSpinner.getSelectedItem();
            userDTO.setNumberOfChildren(numberOfChildren);
            String gender = binding.genderRadioGroup.getCheckedRadioButtonId() == R.id.maleRadioButton ? "Male" : "Female";
            userDTO.setGender(gender);
            String childOfConcern = binding.childOfConcern.getText().toString();
            userDTO.setPersonOfConcernName(childOfConcern);
            String concerns = binding.concernsField.getText().toString();
            userDTO.setConcerns(concerns);

            if (validateForm(firstName, lastName, age, phone, streetAddress, state, zipCode, country,
                    numberOfChildren, gender, concerns)) {
                submitForm();
            }
        });
    }

    private boolean validateForm(String firstName, String lastName, int age, String phone, String streetAddress,
                                 String state, String zipCode, String country, int numberOfChildren,
                                 String gender, String concerns) {
        // Perform form validation here
        boolean isValid = true;

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age == 0 || TextUtils.isEmpty(phone) ||
                TextUtils.isEmpty(streetAddress) || TextUtils.isEmpty(state) ||
                TextUtils.isEmpty(zipCode) || TextUtils.isEmpty(country) ||
                numberOfChildren == 0 || TextUtils.isEmpty(gender) || TextUtils.isEmpty(concerns)) {
            // Set error messages for the empty fields
            if (TextUtils.isEmpty(firstName)) {
                binding.firstNameField.setError("Please enter your first name");
            }
            if (TextUtils.isEmpty(lastName)) {
                binding.lastNameField.setError("Please enter your last name");
            }
            if (TextUtils.isEmpty(phone)) {
                binding.phoneField.setError("Please enter your phone number");
            }
            if (age == 0) {
                Toast.makeText(this, "Please select your age", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(streetAddress)) {
                binding.streetAddressField.setError("Please enter your street address");
            }
            if (TextUtils.isEmpty(state)) {
                binding.stateField.setError("Please enter your state");
            }
            if (TextUtils.isEmpty(zipCode)) {
                binding.zipCodeField.setError("Please enter your zip code");
            }
            if (TextUtils.isEmpty(country)) {
                binding.countryField.setError("Please enter your country");
            }
            if (numberOfChildren == 0) {
                Toast.makeText(this, "Please select the number of children", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(gender)) {
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            }
            if (TextUtils.isEmpty(concerns)) {
                binding.concernsField.setError("Please enter your concerns");
            }
            isValid = false;
        }

        return isValid;

    }

    private void submitForm() {
        boolean isFirstNameEntered = false;

        do {
            // Check if the first name column is not null
            int firstNameIndex = cursor.getColumnIndex("first_name");
            String firstName = cursor.getString(firstNameIndex);

            if (firstName != null) {
                isFirstNameEntered = true;
                @SuppressLint("Range")
                Long userID = cursor.getLong(cursor.getColumnIndex("id"));
                userDTO.setId(userID);

                // Update the existing user data
                sqLiteHelper.updateData(
                        userDTO.getId(),
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getPassword(),
                        userDTO.getEmail(),
                        userDTO.getPhone(),
                        userDTO.getAge(),
                        userDTO.getAgeOfPersonOfConcern(),
                        userDTO.getUserStreet(),
                        userDTO.getUserZip(),
                        userDTO.getParentOrGuardian(),
                        userDTO.getNumberOfChildren(),
                        userDTO.getUserTown(),
                        userDTO.getUserCountry(),
                        userDTO.getPersonOfConcernName(),
                        userDTO.getConcerns(),
                        userDTO.getGender()
                );

                break; // Exit the loop since we found a non-null first name
            }
        } while (cursor.moveToNext());

        if (!isFirstNameEntered) {
            // Insert a new user data
            sqLiteHelper.insertAllData(
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getPassword(),
                    userDTO.getEmail(),
                    userDTO.getPhone(),
                    userDTO.getAge(),
                    userDTO.getAgeOfPersonOfConcern(),
                    userDTO.getUserStreet(),
                    userDTO.getUserZip(),
                    userDTO.getParentOrGuardian(),
                    userDTO.getNumberOfChildren(),
                    userDTO.getUserTown(),
                    userDTO.getUserCountry(),
                    userDTO.getPersonOfConcernName(),
                    userDTO.getConcerns(),
                    userDTO.getGender()
            );

            Toast.makeText(this, "Form submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }

}

