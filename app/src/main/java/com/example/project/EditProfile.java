package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class EditProfile extends AppCompatActivity {
    private AwesomeValidation awesomeValidation;
    Button update;
    EditText a,b,c,d;
    String value,v1,v2,v3,v4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Pofile");
        SharedPreferences result =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        value=result.getString("Value","Data not found");
        String tag="0";
        Log.e(tag,"Username at the edit profile page is"+value);

        SharedPreferences result1 =getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
        v1=result1.getString("Valuee1","Unupdated");


        SharedPreferences result2 =getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
        v2=result2.getString("Valuee2","Unupdated");


        SharedPreferences result3 =getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
        v3=result3.getString("Valuee3","Unupdated");

        SharedPreferences result4 =getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
        v4=result4.getString("Valuee4","Unupdated");



        Log.e(tag,"contact at the edit profile page is"+v1);
        Log.e(tag,"name at the edit profile page is"+v2);
        Log.e(tag,"age at the edit profile page is"+v3);
        Log.e(tag,"area at the edit profile page is"+v4);
        a=(EditText)findViewById(R.id.pce);
        b=(EditText)findViewById(R.id.pne);
        c=(EditText)findViewById(R.id.pae);
        d=(EditText)findViewById(R.id.page);
        a.setText(v1);
        b.setText(v2);
        c.setText(v4);
        d.setText(v3);





        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.pce, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        update=(Button)findViewById(R.id.update_profile);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid mobile number.", Toast.LENGTH_LONG).show();

                } else{
                    String Tag1="2",TAG="33";
                    String type="edit";
                    String p1=b.getText().toString();
                    String p2=a.getText().toString();
                    String p3=d.getText().toString();
                    String p4=c.getText().toString();
                    Log.e(Tag1,"username is being copied is: "+value);
                    Log.e(TAG,"check : "+p1+p2+p3+p4);
                    BackgroundWorker backgroundWorker=new BackgroundWorker(EditProfile.this);
                    backgroundWorker.execute(type,value,p1,p2,p3,p4);


                }
            }
        });

    }

}
