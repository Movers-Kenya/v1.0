package com.example.movers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ErrorActivity extends AppCompatActivity {

    private TextView errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        errorMessage = findViewById(R.id.error_message);

        Intent getErrorIntent = getIntent();
        String error = getErrorIntent.getStringExtra("message");

        errorMessage.setText(error);
        errorMessage.requestFocus();
    }
}
