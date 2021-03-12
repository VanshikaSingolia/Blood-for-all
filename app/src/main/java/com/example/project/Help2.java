package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Help2 extends AppCompatActivity implements View.OnClickListener {

    ImageView _ph1, _msg1, _email1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        setTitle("Contact");

        ViewsInit();


        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home2:
                        startActivity(new Intent(getApplicationContext(), BankDetails.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.help:
                        return true;
                }
                return false;
            }
        });



            }

    private void ViewsInit() {
        _ph1 = (ImageView) findViewById(R.id.PHONE1);
        _ph1.setOnClickListener(Help2.this);
        _msg1 = (ImageView) findViewById(R.id.MSG1);
        _msg1.setOnClickListener(this);
        _email1 = (ImageView) findViewById(R.id.EMAIL1);
        _email1.setOnClickListener(this);
    }

public void onClick(View view) {
        switch (view.getId()) {
        case R.id.EMAIL1:
        Intent mail_ = new Intent(Intent.ACTION_SEND);
        Intent choose_ = new Intent();
        mail_.setData(Uri.parse("mailto:"));
        String[] email_ = {"vanshicusat@gmail.com"};
        mail_.setType("text/plain");
        mail_.putExtra(Intent.EXTRA_EMAIL, email_);
        mail_.setType("message/rfc888");
        choose_ = Intent.createChooser(mail_, "Send Email Via");
        startActivity(choose_);
        break;
        case R.id.MSG1:
        Intent sms_ = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "6200966132", null));
        startActivity(sms_);
        break;
        case R.id.PHONE1:
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:6200966132"));
        startActivity(call);
        break;
    }}


}