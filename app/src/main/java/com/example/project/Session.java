package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class Session {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static final String PREF_NAME            = "LoginPreference";
    private static final String IS_LOGIN             = "IsLoggedIn";

    Context context;
    int PRIVATE_MODE = 0;

    public Session(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession()
    {
        String TAG="0";
        Log.e(TAG,"Login session created");

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();
    }

    public void checkLogin() {

        if (!this.isLoggedIn()) {

            Intent i = new Intent(context, MainActivity.class);
            String TAG="0";
            Log.e(TAG,"Check login fnc called");

            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
    public void logoutUser()
    {

        editor.clear();
        editor.commit();


        Intent i = new Intent(context, MainActivity.class);

        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        // Staring Login Activity
        Toast.makeText(context,"Logged Out", Toast.LENGTH_SHORT).show();
        String TAG="0";
        Log.e(TAG,"Logout session called");
        context.startActivity(i);

    }

    public boolean isLoggedIn()
    {String TAG="0";
        Log.e(TAG,"islogin fnc called");
        return pref.getBoolean(IS_LOGIN, false);
    }

}
