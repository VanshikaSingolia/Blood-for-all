package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddImage extends AppCompatActivity {
    Uri path;
    private String upload_url="http://192.168.43.8/image.php";
    private EditText imagename;
    private Button upload;
    private Bitmap bitmap;
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);
        setTitle("Add Profile Photo");
        upload=(Button)findViewById(R.id.upload);
        imagename=(EditText)findViewById(R.id.imagename);
        ProfileImage=(CircleImageView)findViewById(R.id.cimage);
        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery=new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Tag11="0";
                SharedPreferences result1 =getSharedPreferences("SaveDataa", Context.MODE_PRIVATE);
                final String v1=result1.getString("user","U");
                Log.e(Tag11,"Username  being copied at image side is: "+v1);


                StringRequest stringRequest = new StringRequest(Request.Method.POST, upload_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("Php result",response);
                            JSONObject jsonObject=new JSONObject(response);
                            String Response=jsonObject.getString("response");
                            Toast.makeText(AddImage.this,Response,Toast.LENGTH_LONG).show();
                            Toast.makeText(AddImage.this,"Image Uploaded Successfully",Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(v1,"Connection Error: "+error);
                        Toast.makeText(AddImage.this,"Failed"+error,Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>params=new HashMap<>();
                        params.put("name",imagename.getText().toString().trim());
                        params.put("image",imagetostring(bitmap));
                        params.put("username",v1);

                        return params;
                    }


                };MySingleton.getInstance(AddImage.this).addToRequestQueue(stringRequest);

                Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
            }


        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                ProfileImage.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imagetostring(Bitmap bitmap1)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes=byteArrayOutputStream.toByteArray();
        String temp= Base64.encodeToString(imgbytes,Base64.DEFAULT);
        return temp;
    }


}
