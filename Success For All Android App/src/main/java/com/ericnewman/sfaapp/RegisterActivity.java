package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.ericnewman.sfaapp.database.SQLiteHelper;
import com.ericnewman.sfaapp.dto.UserDTO;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password;
    Button register,
            goToLogin = findViewById(R.id.goLogin),
            notSigningUpBtn = findViewById(R.id.notSingingUp);
    String emailHolder, passwordHolder;
    Boolean aBoolean;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String not_found = "Not_Found";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.signupBtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        sqLiteHelper = new SQLiteHelper(this);
        goToLogin.setOnClickListener(v ->{
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);

        });

        notSigningUpBtn.setOnClickListener(v ->{
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);

        });

        // Adding click listener to register button.
        register.setOnClickListener(view -> {
            // Creating SQLite database if doesn't exists
            SQLiteDataBaseBuild();
            // Creating SQLite table if doesn't exists.
            SQLiteTableBuild();
            // Checking EditText is empty or Not.
            CheckEditTextStatus();
            // Method to check Email is already exists or not.
            CheckingEmailAlreadyExistsOrNot();
            // Empty EditText After done inserting process.
            EmptyEditTextAfterDataInsert();
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**********************************************************************************************
                                                functions
     **********************************************************************************************/
    // SQLite database build method.
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME,
                Context.MODE_PRIVATE, null);
    }
    // SQLite table build method.
    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAM +
                "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + SQLiteHelper.Table_Column_2_Email + " VARCHAR, "
                + SQLiteHelper.Table_Column_3_Password + " VARCHAR);");
    }
    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){
        // If editText is not empty then this block will executed.
        if(aBoolean)
        {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(emailHolder);
            userDTO.setPassword(passwordHolder);
            // SQLite query to insert data into table.
            String SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAM +
                    "(email, password) VALUES ('" + emailHolder + "', '" + passwordHolder + "');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            // Closing SQLite database object.
            sqLiteDatabaseObj.close();
            // Printing toast message after done inserting.
            Toast.makeText(RegisterActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();
        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            Toast.makeText(RegisterActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }
    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        email.getText().clear();
        password.getText().clear();
    }
    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus() {
        // Getting value from All EditText and storing into String Variables.
        emailHolder = email.getText().toString();
        passwordHolder = password.getText().toString();
        if (TextUtils.isEmpty(emailHolder) || TextUtils.isEmpty(passwordHolder)) {
            aBoolean = false;
        } else {
            if (validatePassword(password)) { // check if the two passwords match
                // while it won't be saved, it is to stop malicious users
                Boolean checkUser = sqLiteHelper.checkUserInfo(emailHolder, passwordHolder);
                if (!checkUser) { // If there is no user, create them
                    Boolean insert = sqLiteHelper.insertRegisterData(emailHolder, passwordHolder);
                    if (insert) { // If this new user is added, notify with a message and send them to home
                        Toast.makeText(RegisterActivity.this, "Successful login",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else { // if it doesn't work, notify the user
                        Toast.makeText(RegisterActivity.this, "Unsuccessful login",
                                Toast.LENGTH_SHORT).show();
                    }
                } else { // notify user that this email address is already in use
                    Toast.makeText(RegisterActivity.this, "Account already exists",
                            Toast.LENGTH_SHORT).show();
                }
            } else { // if the password is not valid
                Toast.makeText(RegisterActivity.this, "Password must have an upper and lower case letter and a special character.",
                        Toast.LENGTH_SHORT).show();
            }
            aBoolean = true;
        }
    }


    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAM, null, " "
                + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{emailHolder},
                null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                // If Email is already exists then Result variable value set as Email Found.
                not_found = "Email Found";
                // Closing cursor.
                cursor.close();
            }
        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();
    }
    // Checking result
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if(not_found.equalsIgnoreCase("Email Found"))
        {
            // If email is exists then toast msg will display.
            Toast.makeText(RegisterActivity.this,"Email Already Exists",Toast.LENGTH_LONG).show();
        }
        else {
            // If email already dosen't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
        not_found = "Not_Found" ;
    }

    /**************************************************************************************************
     * As a reminder on regular expressions:
     * A regular expression (regex) defines a search pattern for strings.
     * Quantifiers define how often an element can occur:  ?, *, + and {}
     * a $ signifies the end of the expression
     * a * means it occurs zero or more times, while . will match any character
     * so .*  finds any character sequence. A ? occurs no or one times, so *?
     * tries to find the smallest match. This makes the regular expression stop
     * and at the first match.
     * anything within [] is a set definition, can match the letters
     * anything within [1-4] or [a-c] sets a range for the definition
     *
     * Example: "(?.=*[a-z])(?.=*[A-Z].+$"
     * There are two parts to this within the () and both are positive look ahead assertion.
     * The first (?=.*[a-z]). Within this the ?= can be read as 'Look ahead and assert that the
     * following pattern exists', while .* can be read as 'match any number of characters
     * (except a newline)'. The characters are within the range of all 26 lower case letters.
     * The same applies for the second assertion.
     **************************************************************************************************/
    public boolean validatePassword(EditText password) {
        String pw = password.getText().toString();
        boolean validPW = true;
        String errorMessage = "";

        if (pw.length() < 8 || pw.length() > 25) {
            validPW = false;
            password.requestFocus();
            errorMessage += "Minimum length is 8, maximum is 25\n";
        }
        if (!pw.matches("(?=.*[a-z])(?=.*[A-Z]).+$")) {
            validPW = false;
            password.requestFocus();
            errorMessage += "At least one upper and lowercase letter\n";
        }
        if (!pw.matches("(?=.*\\d).+$")) {
            validPW = false;
            password.requestFocus();
            errorMessage += "At least one number\n";
        }
        if (!pw.matches("(?=.*[!@#_+=$%^&*:?-]).+$")) {
            validPW = false;
            password.requestFocus();
            errorMessage += "At least one special character";
        }

        if (!validPW) {
            password.setError(errorMessage);
        }

        return validPW;
    }

}