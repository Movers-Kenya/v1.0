package com.example.movers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SigninActivity extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button loginButton;
    private Switch rememberMe;

    private TextView usernameError, emailError, passwordError;

    String myName, myEmail, myPassword;

    UserDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        usernameError = findViewById(R.id.username_error);
        emailError = findViewById(R.id.email_error);
        passwordError = findViewById(R.id.password_error);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);

        rememberMe = findViewById(R.id.remember_me);

        loginButton = findViewById(R.id.user_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myName = userName.getText().toString().trim();
                myEmail = userEmail.getText().toString().trim();
                myPassword = userPassword.getText().toString().trim();

                saveUserDetails(myName, myEmail, myPassword);
            }
        });
    }

    private void saveUserDetails(String myName, String myEmail, String myPassword) {
        this.myName = myName;
        this.myEmail = myEmail;
        this.myPassword = myPassword;

        if(myName.isEmpty()) {
            usernameError.setText("Username can not be empty!");
        } else if (myEmail.isEmpty()) {
            emailError.setText("Email can not be empty!");
        } else if (myPassword.isEmpty() || myPassword.length() < 8) {
            passwordError.setText("Passowrd must be 8 characters or more");
        } else {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users");

            //Query userExists = myRef.orderByChild("userEmail").equalTo(myEmail);



            details = new UserDetails();

            details.setUserName(myName);
            details.setUserEmail(myEmail);
            details.setUserPassword(myPassword);

            myRef.push().setValue(details);

            Toast.makeText(SigninActivity.this, "Profile Saved Successfully", Toast.LENGTH_SHORT).show();

            Intent dashboardIntent = new Intent(getApplicationContext(), HomeActivity.class);
            dashboardIntent.putExtra("username", myName);
            dashboardIntent.putExtra("email", myEmail);
            startActivity(dashboardIntent);
        }
    }
}
