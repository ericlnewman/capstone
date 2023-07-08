package com.ericnewman.sfaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ericnewman.sfaapp.database.SQLiteHelper;
import com.ericnewman.sfaapp.dto.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {

    protected EditText email;
    protected Button submit;
    private SQLiteDatabase sqLiteDatabaseObj;
    private final SQLiteHelper sqLiteHelper = new SQLiteHelper(ForgotPasswordActivity.this);
    private Cursor cursor;
    UserDTO userDTO = new UserDTO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.emailField);
        submit = findViewById(R.id.buttonLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailEntry = email.getText().toString().trim();

                // Check if the email is empty
                if (emailEntry.isEmpty()) {
                    // Show an error message
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the new password is empty
                String newPassword = userDTO.getPassword();
                if (newPassword.isEmpty()) {
                    // Show an error message or handle the case where a new password could not be generated
                    return;
                }

                // Update the password in the database
                boolean passwordChanged = sqLiteHelper.updatePassword(emailEntry, newPassword);

                if (passwordChanged) {
                    // Send the password reset email
                    sendPasswordResetEmail(emailEntry);

                    // Show a success message
                    Toast.makeText(ForgotPasswordActivity.this, "Password changed successfully. Please check your email for further instructions.", Toast.LENGTH_SHORT).show();
                } else {
                    // Show an error message
                    Toast.makeText(ForgotPasswordActivity.this, "Failed to change password. Please try again later.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    private void sendPasswordResetEmail(String emailEntry) {
        String randomCharacters = generateRandomCharacters();
        final String senderEmail = emailEntry;
        final String senderPassword = randomCharacters;

        final String emailSubject = "Password Reset Request";
        final String emailContent = "Dear user,\n\nYou have requested to reset your password. Please follow the link below to reset your password.\n\nReset Link: [Put your reset link here]\n\nIf you didn't request this password reset, please ignore this email.\n\nBest regards,\nThe App Team";

        new Thread(() -> {
            // Set up mail server properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Create a mail session with the properties
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                // Create a message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEntry));
                message.setSubject(emailSubject);
                message.setText(emailContent);

                // Send the message
                Transport.send(message);

                runOnUiThread(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                    builder.setMessage("Password reset email sent. Please check your email for further instructions.")
                            .setTitle("Success")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    // Update the password in the SQLite database
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(SQLiteHelper.Table_Column_3_Password, senderPassword);
                    sqLiteDatabaseObj.update(SQLiteHelper.TABLE_NAM, contentValues,
                            SQLiteHelper.Table_Column_2_Email + "=?", new String[]{emailEntry});

                    // Search the SQLite database for the user with the entered email
                    sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
                    cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAM, null, " " +
                                    SQLiteHelper.Table_Column_2_Email + "=?", new String[]{emailEntry},
                            null, null, null);

                    // Check if the cursor has any data
                    if (cursor.moveToFirst()) {
                        // Retrieve the password associated with the entered email
                        @SuppressLint("Range")
                        String password = cursor.getString(
                                cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Password));
                        userDTO.setPassword(password);

                        // Show a success message
                        Toast.makeText(ForgotPasswordActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // Email not found in the database
                        Toast.makeText(ForgotPasswordActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                    }

                    // Close the cursor and database connection
                    cursor.close();
                    sqLiteHelper.close();
                });
            } catch (MessagingException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ForgotPasswordActivity.this);
                    builder.setMessage("Failed to send password reset email.")
                            .setTitle("Error")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                });
            }
        }).start();
    }

    public String generateRandomCharacters() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Generate a random capital letter
        char capitalLetter = (char) (random.nextInt(26) + 'A');
        sb.append(capitalLetter);

        // Generate a random lowercase letter
        char lowercaseLetter = (char) (random.nextInt(26) + 'a');
        sb.append(lowercaseLetter);

        // Generate a random special character
        String specialCharacters = "!@#$%^&*()";
        char specialCharacter = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        sb.append(specialCharacter);

        // Generate five random digits
        for (int i = 0; i < 5; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }


}