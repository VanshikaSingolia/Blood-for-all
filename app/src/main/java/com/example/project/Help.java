package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Help extends AppCompatActivity
{
    ImageView a,b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help3);
        setTitle("Contact");
        a=(ImageView)findViewById(R.id.i11);
        b=(ImageView)findViewById(R.id.i22);
        c=(ImageView)findViewById(R.id.i33);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:6200966132"));
                startActivity(call);
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sms_ = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", "6200966132", null));
                startActivity(sms_);

            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mail_ = new Intent(Intent.ACTION_SEND);
                Intent choose_ = new Intent();
                mail_.setData(Uri.parse("mailto:"));
                String[] email_ = {"vanshicusat@gmail.com"};
                mail_.setType("text/plain");
                mail_.putExtra(Intent.EXTRA_EMAIL, email_);
                mail_.setType("message/rfc888");
                choose_ = Intent.createChooser(mail_, "Send Email Via");
                startActivity(choose_);

            }
        });


    }

}



