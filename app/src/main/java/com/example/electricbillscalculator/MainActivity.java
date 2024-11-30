package com.example.electricbillscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button calc_btn, clear_btn, about_btn;
    EditText eTvalue1, eTvalue2;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calc_btn = findViewById(R.id.calc_btn);
        clear_btn = findViewById(R.id.clear_btn);
        about_btn = findViewById(R.id.about_btn);
        eTvalue1 = findViewById(R.id.eTvalue1);
        eTvalue2 = findViewById(R.id.eTvalue2);
        output = findViewById(R.id.output);

        calc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    double units = Double.parseDouble(eTvalue1.getText().toString());
                    double rebate = Double.parseDouble(eTvalue2.getText().toString());
                    double charge, bills;

                    if (units <= 200) {
                        charge = units * 0.218;}

                    else if (units > 200 && units <= 300) {
                        charge = (200 * 0.218) + ((units - 200) * 0.334);}

                    else if (units > 300 && units <= 600) {
                        charge = (200 * 0.218) + (100 * 0.334) + ((units - 300) * 0.516);}

                    else {
                        charge = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((units - 600) * 0.546);}

                    bills = charge - (charge * rebate / 100);

                    output.setText(String.format("RM %.2f", bills));
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "The units used and rebate rate cannot be empty.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                eTvalue1.setText("");
                eTvalue2.setText("");
                output.setText("RM 0.00");
            }
        });

        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}