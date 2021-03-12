package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessfulPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successpass);
        setTitle("Successful Update");
        Button okk;
        okk=(Button)findViewById(R.id.ok);
        okk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i2 = new Intent(getApplicationContext(), UserHomePage.class);
                startActivity(i2);

            }
        });
    }
}
