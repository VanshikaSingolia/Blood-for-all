package com.example.project;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SentRequestFragment extends Fragment {

    String sent="http://192.168.43.8/sentrequest.php";
    private View mview;


    String uname;


    public SentRequestFragment() {

    }


    public static SentRequestFragment newInstance(String param1, String param2) {
        SentRequestFragment fragment = new SentRequestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        mview= inflater.inflate(R.layout.fragment_sent_req, container, false);

        SharedPreferences result =getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        uname=result.getString("Value","Data not found");
        String TAG="Frag sentreq";
        Log.e(TAG,"Usename at sent request"+uname);
        getdata();
        return  mview;
    }

   private void getdata() {
       final List<Sender> senderList = new ArrayList<>();
        StringRequest stringRequest=new StringRequest(com.android.volley.Request.Method.POST, sent, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String TAG="1";
                Log.e(TAG,"resopnse at sent request ; "+response);
                try {
                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    for (int i = 0; i < array.length(); i++)
                    {

                        //getting product object from json array
                        JSONObject obj = array.getJSONObject(i);
                        Log.e("TAG", "" + obj);
                        String name = obj.getString("Rname");
                        Log.e("TAG", "" + name);
                        String date = obj.getString("Date");
                        Log.e("TAG", "" + date);
                        String email = obj.getString("Rusername");
                        Log.e("TAG", "" + email);
                        String  phn = obj.getString("Rphone");
                        Log.e("TAG", "" + phn);
                        senderList.add(new Sender(name,date,email,phn));


                    }
                    Log.e("TAG", "" + senderList.size());
                    startAdapter(senderList);

                }
                catch (JSONException e) {
                    String TAG1="1";
                    Log.e(TAG1,"Error in fetching data : "+response);
                    Toast.makeText(getActivity(),"Couldn't find data",Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Couldn't fetch data",Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        )
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("sender_name",uname);
                Log.e(uname,": Username");

                return params;
            }};
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    private void startAdapter(List<Sender> senderList)
    {
        RecyclerView sr=(RecyclerView)mview.findViewById(R.id.sr);
        sr.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProgrammingAdapterSender adapter = new ProgrammingAdapterSender(senderList,getActivity());
        sr.setAdapter(adapter);
    }




}
