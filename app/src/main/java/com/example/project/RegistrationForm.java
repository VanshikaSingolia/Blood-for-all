package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class RegistrationForm extends AppCompatActivity  {
    Spinner spinner;
    EditText name,age,phone,area,username,password;
    String result,radio;
    RadioGroup radioGroup;
    RadioButton radioButton;
    SharedPreferences sharedPreferences;

    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;



    ArrayAdapter <CharSequence>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reg_form);
        setTitle("Sign up");
        spinner=(Spinner)findViewById(R.id.spinner);
        adapter= ArrayAdapter.createFromResource(this,R.array.shortcuts,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + "  selected", Toast.LENGTH_LONG).show();
                String value= String.valueOf(parent.getItemAtPosition(position));
                String tag ="1";
                String TAG="0";
                Log.d(tag,"check:"+value);

              int x=1;
                if(x>0)
                switch (position) {
                    case 0:
                        result ="A+ve";
                        break;
                    case 1:
                        result ="A-ve";
                        break;
                    case 2:
                        result ="B+ve";
                        break;
                    case 3:
                        result ="B-ve";
                        break;
                    case 4:
                        result ="AB+ve";
                        break;
                    case 5:
                        result ="AB-ve";
                        break;
                    case 6:
                        result ="O+ve";
                        break;
                    case 7:
                        result ="O-ve";
                        break;

                    default:
                        result = "Computation Error";
                }
                Log.e(TAG, "The option selected is:"+result);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            if(bundle.getString("form")!=null)
            {
                Toast.makeText(getApplicationContext(),"Fill up"+bundle.getString("form"), Toast.LENGTH_SHORT).show();
            }
        }
        //boolean invalid = false;
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        radioGroup=findViewById(R.id.r1);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        phone=(EditText)findViewById(R.id.phone);
        area=(EditText)findViewById(R.id.area);
        username=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.password);



        awesomeValidation.addValidation(this, R.id.mail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.phone, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this,R.id.password,"^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",R.string.passworderror);


    }




    public void checkButton(View view) {
        radioGroup=findViewById(R.id.r1);
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(this,"Selected option: "+radioButton.getText(),Toast.LENGTH_LONG).show();
        String TAGG="10";
        radio= (String) radioButton.getText();
        Log.e(TAGG,"Radio button selected is: "+radio);

    }
        public void onReg(View view)
        {   if (!awesomeValidation.validate()) {
            Toast.makeText(this, "Please enter correct mobile number,email or password", Toast.LENGTH_LONG).show();

        } else{
            String sname=name.getText().toString();
            String sgender=radio;
            String sage=age.getText().toString();
            String sarea=area.getText().toString();
            String susername=username.getText().toString();
            String sbloodgroup=result;
            String spassword=password.getText().toString();
            String sphone=phone.getText().toString();
            String Tag1="2",Tag11="21",TAG="33";
            String type="register";

            Log.e(Tag1,"String is being copied is: "+sbloodgroup);
            Log.e(Tag11,"Gender  being copied is: "+sgender);
            Log.e(TAG,"check : "+sname+sage+sphone+sarea+susername+spassword);

            BackgroundWorker backgroundWorker=new BackgroundWorker(this);
            backgroundWorker.execute(type,sname,sgender,sage,sphone,sarea,susername,spassword,sbloodgroup);

            sharedPreferences=this.getSharedPreferences("SaveDataa", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("user",username.getText().toString());
            editor.apply();
            //save.save(this,"session","true");
            Toast.makeText(this,"Registered Successfully! ",Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(getApplicationContext(),AddImage.class);
            startActivity(i2);

    }
        }



}
