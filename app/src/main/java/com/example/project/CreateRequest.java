package com.example.project;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateRequest extends AppCompatActivity  {
    EditText p_name,p_contact,p_email,_purpose,date1,p_area;
    final Calendar myCalendar = Calendar.getInstance();
    Button create;
    private String create_url="http://192.168.43.8/createrequest.php";
    String dname,demail,dphone,TAG="0";
    Spinner spinner;
    String result;
    //defining AwesomeValidation object
    private AwesomeValidation awesomeValidation;
    ArrayAdapter<CharSequence> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_request);
        setTitle("Create Request");



        spinner=(Spinner)findViewById(R.id.spinner1);
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















        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        p_name=(EditText)findViewById(R.id.pname);
        p_contact=(EditText)findViewById(R.id.pcontact);
        p_email=(EditText)findViewById(R.id.pemail);
        _purpose=(EditText)findViewById(R.id.purpose);
        date1=(EditText) findViewById(R.id.pdate);
        create=(Button)findViewById(R.id.send);
        p_area=(EditText)findViewById(R.id.parea);
        awesomeValidation.addValidation(this, R.id.pemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.pcontact, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };



date1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        new DatePickerDialog(CreateRequest.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }
});


        dname=getIntent().getStringExtra("NAME");
        dphone=getIntent().getStringExtra("PHONE");
        demail=getIntent().getStringExtra("EMAIL");
        Log.e(TAG,"Donor details  being copied at create request id: "+dname+" " +dphone+" "+demail);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!awesomeValidation.validate())
                {
                    Toast.makeText(CreateRequest.this,"Enter valid e-mail and mobile number",Toast.LENGTH_LONG).show();
                }
                else{
                StringRequest stringRequest = new StringRequest(Request.Method.POST, create_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("Php result",response);
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("response");
                            Toast.makeText(CreateRequest.this,"Request sent Successfully",Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG,"Connection Error: "+error);
                        Toast.makeText(CreateRequest.this,"Failed"+error,Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                       // Log.e(TAG,"Sender details  being copied at create request id: "+dt+" " +patient_name+" "+patient_contact);
                        Map<String,String>params=new HashMap<>();
                        params.put("sname",p_name.getText().toString());
                        params.put("sphone",p_contact.getText().toString());
                        params.put("semail",p_email.getText().toString());
                        params.put("sarea",p_area.getText().toString());
                        params.put("sblood",result);
                        params.put("spurpose",_purpose.getText().toString());
                        params.put("rname",dname);
                        params.put("rphone",dphone);
                        params.put("rusername",demail);
                        params.put("date",date1.getText().toString());
                        return params;}
                };
                MySingleton.getInstance(CreateRequest.this).addToRequestQueue(stringRequest);
                Toast.makeText(CreateRequest.this,"Request sent Successfully",Toast.LENGTH_LONG).show();
                Intent i2=new Intent(getApplicationContext(),Search.class);
                startActivity(i2);
            }
            }
        });
    }

    private void updateLabel()
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        date1.setText(sdf.format(myCalendar.getTime()));
    }
}
