package com.example.movers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView errorMessage, havingTrouble;
    private EditText phoneNumber;
    private Button verifyPhoneNumber;

    public String myNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorMessage = findViewById(R.id.error_message);
        havingTrouble = findViewById(R.id.having_trouble);

        phoneNumber = findViewById(R.id.phone_number);

        verifyPhoneNumber = findViewById(R.id.verify_phone_number);

        havingTrouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerComplaint();
            }
        });

        verifyPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myNumber = phoneNumber.getText().toString().trim();

                phoneAuth(myNumber);
            }
        });
    }

    private void registerComplaint() {
        Intent complaintIntent = new Intent(getApplicationContext(), RegisterComplaint.class);
        startActivity(complaintIntent);
    }

    private void phoneAuth(String myNumber) {
        this.myNumber = myNumber;

        if (myNumber.isEmpty() || myNumber.length() < 9) {
            errorMessage.setText("Enter a valid Phone Number");
            errorMessage.requestFocus();
            return;
        }

        Intent verifyPhoneIntent = new Intent(getApplicationContext(), VerifyPhone.class);
        verifyPhoneIntent.putExtra("phone_number", myNumber);
        startActivity(verifyPhoneIntent);
    }
}
