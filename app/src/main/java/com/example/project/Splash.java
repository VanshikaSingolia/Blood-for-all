    package com.example.project;

    import android.content.Intent;
    import android.os.Bundle;
    import android.os.Handler;

    import androidx.appcompat.app.AppCompatActivity;

    public class Splash extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash2);
            Handler hd=new Handler();
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i=new Intent(Splash.this, UserHomePage.class);
                    startActivity(i);
                    finish();
                }
            },3000);
        }
    }
