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


public class ReceivedRequestFragment extends Fragment {

    View view;
    String rename;
    String received="http://192.168.43.8/recrequest.php";

    public ReceivedRequestFragment() {

    }

    public static ReceivedRequestFragment newInstance(String param1, String param2) {
        ReceivedRequestFragment fragment = new ReceivedRequestFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences result =getActivity().getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        rename=result.getString("Value","Data not found");
        String TAG="Frag rec req";
        Log.e(TAG,"Usename at sent request"+rename);
        view=inflater.inflate(R.layout.fragment_rec_req, container, false);
        getdata1();
        return view;
    }

    private void getdata1() {
        final List<Receiver> recList = new ArrayList<>();
        StringRequest stringRequest=new StringRequest(com.android.volley.Request.Method.POST, received, new Response.Listener<String>() {
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
                        String name = obj.getString("Sname");
                        Log.e("TAG", "" + name);
                        String date = obj.getString("Date");
                        Log.e("TAG", "" + date);
                        String email = obj.getString("Semail");
                        Log.e("TAG", "" + email);
                        String  phn = obj.getString("Sphone");
                        Log.e("TAG", "" + phn);
                        String  p = obj.getString("Spurpose");
                        Log.e("TAG", "" + p);
                        String  bl = obj.getString("Sblood");
                        Log.e("TAG", "" + bl);
                        String  area = obj.getString("Sarea");
                        Log.e("TAG", "" + area);




                        recList.add(new Receiver(name,phn,email,p,bl,date,area));


                    }
                    Log.e("TAG", "" + recList.size());
                    startAdapter(recList);




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
                params.put("uusername",rename);
                Log.e(rename,": Username");

                return params;
            }};
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


    }


    private void startAdapter(List<Receiver> receiverList)
    {
        RecyclerView rr=(RecyclerView)view.findViewById(R.id.rr);
        rr.setLayoutManager(new LinearLayoutManager(getActivity()));
        ProgrammingAdapterReceiver adapter = new ProgrammingAdapterReceiver(receiverList,getActivity());
        rr.setAdapter(adapter);
    }
}
