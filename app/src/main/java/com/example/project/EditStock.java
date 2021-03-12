package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditStock extends AppCompatActivity {

    EditText T1,T2,T3,T4,T5,T6,T7,T8;
    SharedPreferences s1,s2,s3,s4,s5,s6,s7,s8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_stock);
        setTitle("Edit Stock");

        T1=(EditText) findViewById(R.id.t1);
        T2=(EditText) findViewById(R.id.t2);
        T3=(EditText) findViewById(R.id.t3);
        T4=(EditText) findViewById(R.id.t4);
        T5=(EditText) findViewById(R.id.t5);
        T6=(EditText) findViewById(R.id.t6);
        T7=(EditText) findViewById(R.id.t7);
        T8=(EditText) findViewById(R.id.t8);
        Button sub;

        sub=(Button)findViewById(R.id.sub);
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                s1=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=s1.edit();
                editor1.putString("Value1",T1.getText().toString());
                editor1.apply();
                s2=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2=s2.edit();
                editor2.putString("Value2",T2.getText().toString());
                editor2.apply();
                s3=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor3=s3.edit();
                editor3.putString("Value3",T3.getText().toString());
                editor3.apply();
                s4=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor4=s4.edit();
                editor4.putString("Value4",T4.getText().toString());
                editor4.apply();
                s5=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor5=s5.edit();
                editor5.putString("Value5",T5.getText().toString());
                editor5.apply();
                s6=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor6=s6.edit();
                editor6.putString("Value6",T6.getText().toString());
                editor6.apply();
                s7=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor7=s7.edit();
                editor7.putString("Value7",T7.getText().toString());
                editor7.apply();
                s8=getSharedPreferences("SaveData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor8=s8.edit();
                editor8.putString("Value8",T8.getText().toString());
                editor8.apply();


                Intent i3 = new Intent(getApplicationContext(), BankDetails.class);
                startActivity(i3);


            }
        });
    }
}
