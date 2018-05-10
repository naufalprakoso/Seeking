package com.njy.seeking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdateCompanyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imgProfile;
    private TextView txtName, txtPhone, txtEmail, txtWebsite, txtAddress, txtDescription;
    private Button btnEditProfile;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_edit_profile:
                
                break;
        }
    }
}
