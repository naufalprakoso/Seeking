package com.njy.seeking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditCompanyProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnEditProfile, btnFindOffice;
    private EditText edtPhone, edtName, edtEmail, edtPassword, edtWebsite, edtDescription;
    private ImageView imgProfile;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_company:
                break;
            case R.id.btn_edit_profile:
                break;
            case R.id.btn_find_address:
                break;
        }
    }
}
