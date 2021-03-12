package com.example.project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
    Button bn;
    Button bn1;

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to quit BloodForAll?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.super.onBackPressed();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
                AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bn = (Button) findViewById(R.id.bn);
        bn1 = (Button) findViewById(R.id.bn1);
        if (findViewById(R.id.fragment_container) != null) {
        if (savedInstanceState != null) {
                return;
           }
            bn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    startFragment(new UserLoginFragment());
                }
            });        }
            bn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startFragment(new BankLoginFragnent());
                }
            });


    }

    private void startFragment(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container,fragment);
        transaction.commit();
    }


}
