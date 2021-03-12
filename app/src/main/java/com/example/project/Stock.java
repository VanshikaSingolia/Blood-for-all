package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Stock extends AppCompatActivity {
    String v1,v2,v3,v4,v5,v6,v7,v8;
    TextView N1,N2,N3,N4,N5,N6,N7,N8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        setTitle("Stock");
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), UserHomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:

                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Request.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfile.class));
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
        String v1=result1.getString("Value1","DNA");


        SharedPreferences result2 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v2=result2.getString("Value2","DNA");


        SharedPreferences result3 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v3=result3.getString("Value3","DNA");

        SharedPreferences result4 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v4=result4.getString("Value4","DNA");


        SharedPreferences result5 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v5=result5.getString("Value5","DNA");


        SharedPreferences result6 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v6=result6.getString("Value6","DNA");

        SharedPreferences result7 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v7=result7.getString("Value7","DNA");

        SharedPreferences result8 =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        String v8=result8.getString("Value8","DNA");


        N1.setText(v1);
        N2.setText(v2);
        N3.setText(v3);
        N4.setText(v4);
        N5.setText(v5);
        N6.setText(v6);
        N7.setText(v7);
        N8.setText(v8);
    }
}
