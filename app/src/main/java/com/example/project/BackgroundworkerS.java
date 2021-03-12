package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundworkerS extends AsyncTask<String,Void,String>
{   Context context;
    AlertDialog alertDialog;
    BackgroundworkerS(Context ctx1)
    {
        context = ctx1;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0],TAGG="99";
        String status_url="http://192.168.43.8/status.php";
        if(type.equals("status"))
        {
            try {
                String user_name=params[1];
                String status_=params[2];
                Log.e(TAGG,"Arguments at the background are: "+type+user_name+status_);

                URL url=new URL(status_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
                        +"&"+URLEncoder.encode("status_","UTF-8")+"="+URLEncoder.encode(status_,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        String Tag="1";
        Log.e(Tag,"value:"+result);
        if(result.equals("Updated")) {
            Toast.makeText(context, "Donor status successfully updated", Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, UserHomePage.class);
            context.startActivity(i);
        }
        else
        {

            Toast.makeText(context, "Failed to update.Retry.", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
