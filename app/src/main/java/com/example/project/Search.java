package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search extends AppCompatActivity implements SearchView.OnQueryTextListener {
    String address = "http://192.168.43.231/data.php";
    String Dname;
    ProgrammingAdapterDonor adapter;
    List<Donor> donorList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Find Donor");


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), UserHomePage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(), Stock.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.search:

                        return true;
                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Request.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });


        getData();


    }

    private void getData() {
        donorList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST, address, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String TAG = "1";
                Log.e(TAG, "resopnse at sent request ; " + response);
                try {
                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++) {

                        //getting product object from json array
                        JSONObject obj = array.getJSONObject(i);


                        Log.e("TAG", "" + obj);
                        Dname = obj.getString("NAME");
                        Log.e("TAG", "" + Dname);
                        String area = obj.getString("AREA");
                        Log.e("TAG", "" + area);
                        String email = obj.getString("USERNAME");
                        Log.e("TAG", "" + email);
                        String phn = obj.getString("PHONE");
                        Log.e("TAG", "" + phn);
                        String sta = obj.getString("STATUS");
                        Log.e("TAG", "" + sta);
                        String bg = obj.getString("BLOODGROUP");
                        Log.e("TAG", "" + bg);
                        String imagepath = "http://192.168.43.8/uploads/" + obj.getString("PHOTOGRAPH") + ".jpg";
                        Log.e("TAG", "" + imagepath);


                        donorList.add(new Donor(Dname, bg, area, email, phn, sta, imagepath));


                    }
                    Log.e("Size of Donor list", "" + donorList.size());
                    startAdapter(donorList);


                } catch (JSONException e) {
                    String TAG1 = "1";
                    Log.e(TAG1, "Error in fetching data : " + response);
                    Toast.makeText(Search.this, "Couldn't find data", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(Search.this, "Couldn't fetch Donor's data", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();


                return params;
            }
        };

        MySingleton.getInstance(Search.this).addToRequestQueue(stringRequest);


    }


    private void startAdapter(List<Donor> donorList)
    {
        RecyclerView pl = (RecyclerView) findViewById(R.id.pl);
        pl.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProgrammingAdapterDonor(Search.this, donorList);
        pl.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setQueryHint("Name/Area/Status/BloodGroup");
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        String userInput = newText.toLowerCase();
        String TAG1 = "1";
        Log.e(TAG1, "Converted String :" + userInput);
        List<Donor> newList = new ArrayList<>();

        for (Donor item : donorList)
        {
            if ((item.getD_name().toLowerCase().contains(userInput))||(item.getD_area().toLowerCase().contains(userInput))||(item.getD_blood().toLowerCase().contains(userInput))||(item.getD_status().toLowerCase().contains(userInput)))
            {
                newList.add(item);
            }


        }
        adapter.updateList(newList);
        return false;
    }
}
