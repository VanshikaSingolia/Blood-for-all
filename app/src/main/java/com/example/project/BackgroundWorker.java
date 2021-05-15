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

public class BackgroundWorker extends AsyncTask<String,Void,String>
{
    Context context;
AlertDialog alertDialog;
BackgroundWorker(Context ctx)
{
    context = ctx;
}
    @Override
    protected String doInBackground(String... params) {
        String type=params[0];
        String login_url="http://192.168.43.231/login.php";
        String register_url="http://192.168.43.231/register.php";
        String editprofile_url="http://192.168.43.231/editprofile.php";

        if(type.equals("login"))
        {
            try {
            String user_name=params[1];
            String pass_word=params[2];
            URL url=new URL(login_url);
            String TAG="0";
            Log.e(TAG,"check : "+user_name+pass_word);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+URLEncoder.encode("pass_word","UTF-8")+"="+URLEncoder.encode(pass_word,"UTF-8");
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
        else if(type.equals("register"))
        {
            try {
                String name_=params[1];
                String gender_=params[2];
                String age_=params[3];
                String phone_=params[4];
                String area_=params[5];
                String username_=params[6];
                String password_=params[7];
                String bloodgroup_=params[8];


                String Tag2="3";
                Log.e(Tag2,"Blood group  being copied is at background: "+bloodgroup_);
                Log.e(Tag2,"Gender being copied is at background: "+gender_);



                URL url=new URL(register_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data= URLEncoder.encode("nname","UTF-8")+"="+URLEncoder.encode(name_,"UTF-8")
                         +"&"+URLEncoder.encode("ggender","UTF-8")+"="+URLEncoder.encode(gender_,"UTF-8")
                        +"&"+URLEncoder.encode("aage","UTF-8")+"="+URLEncoder.encode(age_,"UTF-8")
                        +"&"+URLEncoder.encode("pphone","UTF-8")+"="+URLEncoder.encode(phone_,"UTF-8")
                        +"&"+URLEncoder.encode("aarea","UTF-8")+"="+URLEncoder.encode(area_,"UTF-8")
                        +"&"+URLEncoder.encode("uusername","UTF-8")+"="+URLEncoder.encode(username_,"UTF-8")
                        +"&"+URLEncoder.encode("ppassword","UTF-8")+"="+URLEncoder.encode(password_,"UTF-8")
                       +"&"+URLEncoder.encode("bbloodgroup","UTF-8")+"="+URLEncoder.encode(bloodgroup_,"UTF-8");

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

        {  if(type.equals("edit"))
            try {
                String user_name=params[1];
                String name_=params[2];
                String ph_=params[3];
                String age_=params[4];
                String area_=params[5];
                String TAG="33";
                Log.e(TAG,"check at background:  "+user_name+name_+ph_+age_+area_);

                URL url=new URL(editprofile_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data= URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")
                        +"&"+URLEncoder.encode("name_","UTF-8")+"="+URLEncoder.encode(name_,"UTF-8")
                        +"&"+URLEncoder.encode("ph_","UTF-8")+"="+URLEncoder.encode(ph_,"UTF-8")
                        +"&"+URLEncoder.encode("age_","UTF-8")+"="+URLEncoder.encode(age_,"UTF-8")
                        +"&"+URLEncoder.encode("area_","UTF-8")+"="+URLEncoder.encode(area_,"UTF-8");

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
        //alertDialog=new AlertDialog.Builder(context).create();
       // alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
      //  alertDialog.setMessage(result);
       // alertDialog.show();
        if(result.equals("Login Succesful")) {
            Toast.makeText(context, "Login Successful",Toast.LENGTH_LONG).show();
            String Tag2="3";
            Log.e(Tag2,"User Log-in Successful");
            Session s=new Session(context);
            s.createLoginSession();
            Intent i = new Intent(context, UserHomePage.class);
            context.startActivity(i);

        }
        else if(result.equals("Insert Succesful")){
            Toast.makeText(context, "Successfully registered",Toast.LENGTH_LONG).show();
            String Tag2="3";
            Log.e(Tag2,"User registration  Successful");
        }
        else if(result.equals("Updated")){
            Toast.makeText(context, "Successfully updated",Toast.LENGTH_LONG).show();
            String Tag2="3";
            Log.e(Tag2,"Details updated Successfully");
            Intent i1 = new Intent(context, UserHomePage.class);
            context.startActivity(i1);

        }



        else {
           Toast.makeText(context, "Operation failed:Re-check credentials",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
