package com.njy.seeking.company;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class UpdateCompanyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgProfile;
    private TextView txtName, txtPhone, txtEmail, txtWebsite, txtAddress, txtDescription;
    private Button btnEditProfile;

    SharedPreferences sharedPreferences;
    String name, phone, email, website, address, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_company_profile);

        setTitle(getString(R.string.titleCompanyUpdateProfile));

        imgProfile = (ImageView) findViewById(R.id.img_company);
        txtName = (TextView) findViewById(R.id.txt_company_name);
        txtPhone = (TextView) findViewById(R.id.txt_phone);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        txtWebsite = (TextView) findViewById(R.id.txt_website);
        txtAddress = (TextView) findViewById(R.id.txt_address);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        btnEditProfile = (Button) findViewById(R.id.btn_edit_profile);

        btnEditProfile.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        name = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);
        phone = sharedPreferences.getString(KEY.PHONE_COMPANY_KEY, null);
        email = sharedPreferences.getString(KEY.EMAIL_COMPANY_KEY, null);
        website = sharedPreferences.getString(KEY.WEBSITE_COMPANY_KEY, null);
        address = sharedPreferences.getString(KEY.ADDRESS_COMPANY_KEY, null);
        description = sharedPreferences.getString(KEY.DESCRIPTION_COMPANY_KEY, null);

        txtName.setText(name);
        txtPhone.setText(phone);
        txtEmail.setText(email);
        txtWebsite.setText(website);
        txtAddress.setText(address);
        txtDescription.setText(description);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_profile:
                Intent i = new Intent(getApplicationContext(), EditCompanyProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
