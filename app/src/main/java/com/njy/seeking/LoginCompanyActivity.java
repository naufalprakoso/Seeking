package com.njy.seeking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.njy.seeking.data.KEY;

public class LoginCompanyActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtEmail, edtPassword;
    private TextView txtDisini;
    private Button btnLogin;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;
    String email, password, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_company);

        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        txtDisini = (TextView) findViewById(R.id.txt_disini);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        txtDisini.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        email = sharedPreferences.getString(KEY.EMAIL_COMPANY_KEY, null);
        password = sharedPreferences.getString(KEY.PASSWORD_COMPANY_KEY, null);
        name = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);

        mEdit = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                String tempEmail, tempPassword;

                tempEmail = edtEmail.getText().toString();
                tempPassword = edtPassword.getText().toString();

                if(tempEmail != email ||  tempPassword != password){
                    edtEmail.setError("Invalid email or password");
                }else{
                    mEdit.putBoolean(KEY.FIRST_LOGIN_KEY, true);
                    mEdit.putString(KEY.ROLE_KEY, "Company");
                    mEdit.commit();

                    Intent i = new Intent(getApplicationContext(), CompanyHomeActivity.class);
                    startActivity(i);
                    finish();

                    Toast.makeText(this, "Welcome " + name + "!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txt_disini:
                Intent i = new Intent(getApplicationContext(), RegisterCompanyActivity.class);
                startActivity(i);
                break;
        }
    }
}
