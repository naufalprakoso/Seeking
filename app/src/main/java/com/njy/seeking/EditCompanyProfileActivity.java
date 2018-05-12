package com.njy.seeking;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.njy.seeking.data.KEY;

public class EditCompanyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEditProfile, btnFindOffice;
    private EditText edtPhone, edtName, edtEmail, edtPassword, edtWebsite, edtDescription;
    private ImageView imgProfile;

    String phone, name, email, password, website, description;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company_profile);

        edtPassword = (EditText) findViewById(R.id.edt_password);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtName = (EditText) findViewById(R.id.edt_company_name);
        edtWebsite = (EditText) findViewById(R.id.edt_website);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtDescription = (EditText) findViewById(R.id.edt_description);
        imgProfile = (ImageView) findViewById(R.id.img_company);
        btnEditProfile = (Button) findViewById(R.id.btn_edit_profile);
        btnFindOffice = (Button) findViewById(R.id.btn_find_address);

        btnEditProfile.setOnClickListener(this);
        btnFindOffice.setOnClickListener(this);
        imgProfile.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        mEdit = sharedPreferences.edit();

        phone = sharedPreferences.getString(KEY.PHONE_COMPANY_KEY, null);
        name = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);
        email = sharedPreferences.getString(KEY.EMAIL_COMPANY_KEY, null);
        password = sharedPreferences.getString(KEY.PASSWORD_COMPANY_KEY, null);
        website = sharedPreferences.getString(KEY.WEBSITE_COMPANY_KEY, null);
        description = sharedPreferences.getString(KEY.DESCRIPTION_COMPANY_KEY, null);

        edtPassword.setText(password);
        edtEmail.setText(email);
        edtName.setText(name);
        edtWebsite.setText(website);
        edtPhone.setText(phone);
        edtDescription.setText(description);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_company:
                break;
            case R.id.btn_edit_profile:
                String getPhone, getName, getEmail, getPassword, getWebsite, getDescription;

                getPhone = edtPhone.getText().toString();
                getName = edtName.getText().toString();
                getEmail = edtEmail.getText().toString();
                getPassword = edtPassword.getText().toString();
                getWebsite = edtWebsite.getText().toString();
                getDescription = edtDescription.getText().toString();

                mEdit.putString(KEY.PHONE_COMPANY_KEY, getPhone);
                mEdit.putString(KEY.NAME_COMPANY_KEY, getName);
                mEdit.putString(KEY.EMAIL_COMPANY_KEY, getEmail);
                mEdit.putString(KEY.PASSWORD_COMPANY_KEY, getPassword);
                mEdit.putString(KEY.WEBSITE_COMPANY_KEY, getWebsite);
                mEdit.putString(KEY.DESCRIPTION_COMPANY_KEY, getDescription);
                mEdit.commit();

                Toast.makeText(this, "Update successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_find_address:
                break;
        }
    }
}
