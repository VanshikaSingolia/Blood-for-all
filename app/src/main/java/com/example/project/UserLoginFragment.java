package com.example.project;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;



/**
 * A simple {@link Fragment} subclass.
 */
public class UserLoginFragment extends Fragment {

    private String TAG = "FragOne";
    private View mRootView;
    EditText user1;
    EditText pass1;
    SharedPreferences sharedPreferences;
    Button shareBtn;
    public UserLoginFragment() { }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"called : Oncreate");
        mRootView = inflater.inflate(R.layout.fragment_one_layout, container, false);
         user1=mRootView.findViewById(R.id.uname);
         pass1=mRootView.findViewById(R.id.pass1);
        clickListener();

        return mRootView;

    }

    private void clickListener() {
        Button loginBtn = mRootView.findViewById(R.id.login_btn_donar);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferences=getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("Value",user1.getText().toString());
                editor.apply();
                String username1=user1.getText().toString();
                String password1=pass1.getText().toString();
                String type="login";
                Log.e(TAG,"check : "+username1+password1);
                BackgroundWorker backgroundWorker=new BackgroundWorker(getActivity());
                backgroundWorker.execute(type,username1,password1);




            }


        });
            Button signupBtn = mRootView.findViewById(R.id.sign);
            signupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i1 = new Intent(getActivity(), RegistrationForm.class);
                    startActivity(i1);
                }
            });

        Button shareBtn = mRootView.findViewById(R.id.share);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String sharebody ="Install my app when it is completed...peekaboo";
                String sharesub ="Your sub here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                myIntent.putExtra(Intent.EXTRA_TEXT,sharebody);
                startActivity(Intent.createChooser(myIntent,"Share using"));
            }
        });


    }}

