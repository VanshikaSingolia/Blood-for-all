package com.example.project;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHomePage extends AppCompatActivity
{
    String radio1;
    RadioGroup radioGroup1;
    RadioButton radioButton1;
    private Session mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSession = new Session(getApplicationContext());
        String Tag2="3";
        Log.e(Tag2,"A");
        if(!mSession.isLoggedIn())
        {
            String Tag4="3";
            Log.e(Tag2,"B");
            mSession.checkLogin();
            finish();

            return;
        }
        String Tag3="C";
        Log.e(Tag2,"C");
        setContentView(R.layout.activity_userdetails);
        setTitle("Home");

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Stock.class));
                        overridePendingTransition(0,0);
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
        Button forgot;
        forgot=(Button)findViewById(R.id.forgot_password);
        forgot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);


            }
        });
        Button compt;
        compt=(Button)findViewById(R.id.comp);
        compt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i1 = new Intent(getApplicationContext(), Compatiblity.class);
                startActivity(i1);


            }
        });
        Button out;
        out=(Button)findViewById(R.id.signout);
        out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
               // save.save(userdetails.this,"session","false");
               // Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
              //  startActivity(i2);

              Session mSession = new Session(getApplicationContext());
              mSession.logoutUser();

            }
        });
    Button rem;
    rem=(Button)findViewById(R.id.reminder);
        rem.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            // Code here executes on main thread after user presses button
            Intent i3=new Intent(AlarmClock.ACTION_SET_ALARM);
            i3.putExtra(AlarmClock.EXTRA_HOUR,10);
            i3.putExtra(AlarmClock.EXTRA_MINUTES,20);
            startActivity(i3);


        }
    });
        Button help;
        help=(Button)findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent i3=new Intent(getApplicationContext(),Help.class);
                startActivity(i3);


            }
        });




    }



        public void checkButton1(View view) {
            radioGroup1=findViewById(R.id.r4);
            int radioId1=radioGroup1.getCheckedRadioButtonId();
            radioButton1=findViewById(radioId1);
            Toast.makeText(this,"Selected option: "+radioButton1.getText(),Toast.LENGTH_LONG).show();
            String TAGG="10";
            radio1= (String) radioButton1.getText();
            Log.e(TAGG,"Radio button selected is: "+radio1);
            SharedPreferences result =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
            String value=result.getString("Value","Data not found");
            String status=radio1,name=value;
            Log.e(TAGG,"Shared preference value "+value);
            String type="status";
            BackgroundworkerS backgroundworkerS=new BackgroundworkerS(this);
            backgroundworkerS.execute(type,name,status);
            Log.e(TAGG,"Arguments are: "+type+name+status);

        }

    }




