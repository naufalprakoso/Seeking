package com.njy.seeking.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class RegisterJobSeekerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName, edtPassword, edtConfirmPassword, edtProvince, edtCountry, edtEmail;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_job_seeker);

        setTitle("Job Seeker Registration");

        edtName = (EditText) findViewById(R.id.edt_seeker_name);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
        edtProvince = (EditText) findViewById(R.id.edt_province);
        edtCountry = (EditText) findViewById(R.id.edt_country);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        btnRegister = (Button) findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String name, password, confirmPassword, province, country, email;

                name = edtName.getText().toString();
                password = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();
                province = edtProvince.getText().toString();
                country = edtCountry.getText().toString();
                email = edtEmail.getText().toString();

                if(!confirmPassword.equals(password)){
                    Toast.makeText(this, "Confirm password must same with password", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(), CompleteSeekerActivity.class);
                    i.putExtra(KEY.NAME_SEEKER_KEY, name);
                    i.putExtra(KEY.PASSWORD_SEEKER_KEY, password);
                    i.putExtra(KEY.PROVINCE_SEEKER_KEY, province);
                    i.putExtra(KEY.COUNTRY_SEEKER_KEY, country);
                    i.putExtra(KEY.EMAIL_SEEKER_KEY, email);
                    startActivity(i);
                }

                break;
        }
    }
}
