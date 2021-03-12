package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BankDetails extends AppCompatActivity {
    TextView N1,N2,N3,N4,N5,N6,N7,N8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankdetails);
        setTitle("Home");
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            if(bundle.getString("blue")!=null)
            {
                Toast.makeText(getApplicationContext(),"Fill up"+bundle.getString("blue"), Toast.LENGTH_SHORT).show();
            }
        }

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home2:
                        return true;

                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Help2.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        N1=(TextView)findViewById(R.id.n1);
        N2=(TextView)findViewById(R.id.n2);
        N3=(TextView)findViewById(R.id.n3);
        N4=(TextView)findViewById(R.id.n4);
        N5=(TextView)findViewById(R.id.n5);
        N6=(TextView)findViewById(R.id.n6);
        N7=(TextView)findViewById(R.id.n7);
        N8=(TextView)findViewById(R.id.n8);
        SharedPreferences result1 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v1=result1.getString("Value1","Unupdated");


        SharedPreferences result2 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v2=result2.getString("Value2","Unupdated");


        SharedPreferences result3 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v3=result3.getString("Value3","Unupdated");

        SharedPreferences result4 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v4=result4.getString("Value4","Unupdated");


        SharedPreferences result5 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v5=result5.getString("Value5","Unupdated");


        SharedPreferences result6 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v6=result6.getString("Value6","Unupdated");

        SharedPreferences result7 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v7=result7.getString("Value7","Unupdated");

        SharedPreferences result8 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v8=result8.getString("Value8","Unupdated");


        N1.setText(v1);
                N2.setText(v2);
                N3.setText(v3);
                N4.setText(v4);
                N5.setText(v5);
                N6.setText(v6);
                N7.setText(v7);
                N8.setText(v8);

        Button edit;
        edit=(Button)findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i2 = new Intent(getApplicationContext(),EditStock.class);
                startActivity(i2);



            }
        });
        Button log;
        log=(Button)findViewById(R.id.sout);
        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Toast.makeText(BankDetails.this,"Logget out",Toast.LENGTH_LONG).show();
                Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);




            }
        });

    }}

