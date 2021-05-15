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

public class BackgroundWorkerr extends AsyncTask<String,Void,String>
{   Context contextt;
    AlertDialog alertDialog;
    BackgroundWorkerr(Context ctxx)
    {
        contextt = ctxx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type=params[0],TAG="1";
        String login_url="http://192.168.43.231/login2.php";
        if(type.equals("loginn"))
        {
            Log.e(TAG,"type obtained"+type);
            try {
                String user_namee=params[1];
                String pass_wordd=params[2];
                Log.e(TAG,"obtained"+user_namee+pass_wordd);
                URL url=new URL(login_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data= URLEncoder.encode("user_name1","UTF-8")+"="+URLEncoder.encode(user_namee,"UTF-8")+"&"+URLEncoder.encode("pass_word1","UTF-8")+"="+URLEncoder.encode(pass_wordd,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result1="";
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    result1+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result1;
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

        if(result.equals("Login Succesful")) {

            Intent i = new Intent(contextt, BankDetails.class);
            contextt.startActivity(i);
        }

        else {
            Toast.makeText(contextt, "Operation failed",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}

