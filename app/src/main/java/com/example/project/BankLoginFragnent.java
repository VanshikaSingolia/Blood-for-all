package com.example.project;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankLoginFragnent extends Fragment  {
    EditText user2;
    EditText pass2;
    private String TAG = "FragTwo";
    private View mRootView;
    MainActivity mActivity;

    TextView textView;
    public BankLoginFragnent() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG,"called : Oncreate");
        mRootView = inflater.inflate(R.layout.fragment_two_layout, container, false);
        user2=mRootView.findViewById(R.id.uname);
        pass2=mRootView.findViewById(R.id.pass);
        clickListener();
        mActivity=(MainActivity)getActivity();



        return mRootView;
    }

    private void clickListener() {
        Button loginBtn = mRootView.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG," log in button clicked");
                String username2=user2.getText().toString();
                String password2=pass2.getText().toString();
                String type="loginn";
                Log.e(TAG,"check : "+username2+password2);
                BackgroundWorkerr backgroundWorkerr=new BackgroundWorkerr(getActivity());
                backgroundWorkerr.execute(type,username2,password2);
            }
        });

    }



}

