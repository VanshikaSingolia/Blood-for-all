package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
TextView t;
CircleImageView dp;
RequestQueue rq;
SharedPreferences sharedPreferences,sharedPreferences1,sharedPreferences2,sharedPreferences3;
TextView ps,pb,pa,pn,pc;
String status,blood,contact,age,imagename,name,area,value;
String profile_details_url="http://192.168.43.8/userprofile.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("User Profile");
        setContentView(R.layout.activity_userprofile);
        t=(TextView) findViewById(R.id.key);
        dp=(CircleImageView) findViewById(R.id.dp);
        ps=(TextView) findViewById(R.id.ps);
        pb=(TextView) findViewById(R.id.pb);
        pa=(TextView) findViewById(R.id.pa);
        pn=(TextView) findViewById(R.id.pn);
        pc=(TextView) findViewById(R.id.pc);


        SharedPreferences result =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        value=result.getString("Value","Data not found");
        t.setText(value);


        sendjsonrequest();





        FloatingActionButton floatingActionButton=findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), UserHomePage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Stock.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), Search.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Request.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:

                        return true;

                }
                return false;
            }
        });
    }
public void setimage(String URL){
    Log.e("TAG","At image"+URL);
    rq= Volley.newRequestQueue(this);
    ImageRequest imageRequest=new ImageRequest(URL,
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    dp.setImageBitmap(response);
                    String tag="0";
                    Log.e(tag,"The image response is"+response);
                }
            }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"Couldn't find image",Toast.LENGTH_LONG).show();
            error.printStackTrace();
        }
    }
    );
    MySingleton.getInstance(UserProfile.this).addToRequestQueue(imageRequest);
}
    public void sendjsonrequest()
    {
        StringRequest stringRequest=new StringRequest(com.android.volley.Request.Method.POST, profile_details_url, new Response.Listener<String>() {
            @Override



            public void onResponse(String response) {
                String TAG="1";
                Log.e(TAG,"resopnse is ; "+response);
                try {



                    JSONObject obj= new JSONArray(response).getJSONObject(0);
                    Log.e("TAG",""+obj);
                    name= obj.getString("NAME");
                    Log.e("TAG",""+name);
                    status= obj.getString("STATUS");
                    Log.e("TAG",""+status);
                    blood= obj.getString("BLOODGROUP");
                    Log.e("TAG",""+blood);
                    contact= obj.getString("PHONE");
                    Log.e("TAG",""+contact);
                    age= obj.getString("AGE");
                    Log.e("TAG",""+age);
                    imagename= obj.getString("PHOTOGRAPH");
                    Log.e("TAG",""+imagename);
                    area= obj.getString("AREA");
                    Log.e("TAG",""+area);
                    ps.setText(status);
                    pb.setText(blood);
                    pc.setText(contact);
                    pn.setText(name);
                    pa.setText(age);
                    sharedPreferences=getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Valuee1",pc.getText().toString());
                    editor.apply();
                    sharedPreferences1=getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
                    editor=sharedPreferences1.edit();
                    editor.putString("Valuee2",pn.getText().toString());
                    editor.apply();
                    sharedPreferences2=getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2=sharedPreferences2.edit();
                    editor2.putString("Valuee3",pa.getText().toString());
                    editor2.apply();
                    sharedPreferences3=getSharedPreferences("SaveData1", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor3=sharedPreferences3.edit();
                    editor3.putString("Valuee4",area);
                    editor3.apply();
                    String server_url="http://192.168.43.8/uploads/"+imagename+".jpg";
                    Log.e("TAG","in fnc"+server_url);
                    setimage(server_url);

                }



                catch (JSONException e) {
                    String TAG1="1";
                    Log.e(TAG1,"Error in fetching data : "+response);
                    Toast.makeText(getApplicationContext(),"Couldn't find data",Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Couldn't fetch data",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }


        )
        {
            protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String>params=new HashMap<>();
            params.put("user_name",value);

            return params;
        }};
        MySingleton.getInstance(UserProfile.this).addToRequestQueue(stringRequest);

}}
