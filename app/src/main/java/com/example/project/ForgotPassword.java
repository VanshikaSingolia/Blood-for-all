package com.example.project;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.basgeekball.awesomevalidation.AwesomeValidation;
        import com.basgeekball.awesomevalidation.ValidationStyle;

public class ForgotPassword extends AppCompatActivity {
    TextView t;
    EditText passo;
    EditText passn;
    String value;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        setTitle("Reset Password");
        Button re;
        re=(Button)findViewById(R.id.reset);
        t=(TextView) findViewById(R.id.confirm);
        SharedPreferences result =getSharedPreferences("SaveData", Context.MODE_PRIVATE);
        value=result.getString("Value","Data not found");
        t.setText(value);
        passo=(EditText)findViewById(R.id.current);
        passn=(EditText)findViewById(R.id.next);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.password,"^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",R.string.passworderror);
        re.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(), "Please enter correct mobile number,email or password", Toast.LENGTH_LONG).show();
                }else{
                String username1=value;
                String password1=passo.getText().toString();
                String password2=passn.getText().toString();
                if(username1.equals("")||password1.equals("")||password2.equals("")) {
                    Toast.makeText(ForgotPassword.this,"All fields are mandatory.",Toast.LENGTH_LONG).show();
                }else{
                    String type = "password", TAG = "1";
                    Log.e(TAG, "check : " + username1 + password1 + password2);
                    BackgroundWorkerP backgroundWorkerP = new BackgroundWorkerP(getApplication());
                    backgroundWorkerP.execute(type, username1, password1, password2);
                    Intent i = new Intent(getApplicationContext(), SuccessfulPassword.class);
                    startActivity(i);

                }}
            }
        });
    }
}
