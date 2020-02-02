package com.example.movers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent getUsername = getIntent();
        String name = getUsername.getStringExtra("username");

        welcome = findViewById(R.id.welcome);
        welcome.setText(name);

        populateSpinner();

        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(testIntent);
            }
        });

    }

    private void populateSpinner() {
        List<String> myArraySpinner = new ArrayList<String>();
        myArraySpinner.add("Valuables and Fragile Cargo");
        myArraySpinner.add("Construction Goods and Materials");
        myArraySpinner.add("House Equipment");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myArraySpinner);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner dropdownSpinner = findViewById(R.id.goods_type_spinner);

        dropdownSpinner.setAdapter(spinnerAdapter);

    }
}
