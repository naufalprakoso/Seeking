package com.njy.seeking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.njy.seeking.data.KEY;

public class RegisterCompanyActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister;
    private EditText edtName, edtPassword, edtConfirmPassword, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_company);

        setTitle(getString(R.string.titleCompanyRegistration));

        edtName = (EditText) findViewById(R.id.edt_company_name);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
        edtEmail = (EditText) findViewById(R.id.edt_email);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String strPassword, strConfirmPassword, strName, strEmail;

                strPassword = edtPassword.getText().toString();
                strConfirmPassword = edtConfirmPassword.getText().toString();
                strName = edtName.getText().toString();
                strEmail = edtEmail.getText().toString();

                if (strName.isEmpty()){
                    edtName.setError(getString(R.string.mustBeFilled));
                }else if (strPassword.isEmpty()){
                    edtPassword.setError(getString(R.string.mustBeFilled));
                }else if(strEmail.isEmpty()){
                    edtEmail.setError(getString(R.string.mustBeFilled));
                }else if (strName.length() > 35){
                    edtName.setError("Name cannot more than 35 words");
                }else if(edtPassword != edtConfirmPassword){
                    edtPassword.setError("Not same with confirm password");
                }else{
                    Intent i = new Intent(getApplicationContext(), CompleteCompanyActivity.class);
                    i.putExtra(KEY.NAME_COMPANY_KEY, strName);
                    i.putExtra(KEY.EMAIL_COMPANY_KEY, strEmail);
                    i.putExtra(KEY.PASSWORD_COMPANY_KEY, strPassword);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }
}
