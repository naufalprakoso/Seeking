package com.njy.seeking.seeker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class CompleteSeekerCertificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spinnerCertificationCompany;
    private EditText edtCertificationId;
    private Button btnRegister;

    String name, password, province, country, email,
            phone, website, linkedin, github, birthdayPlace, birthday, lastEducation,
            certificationId, certificationCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_seeker_certification);

        setTitle("Job Seeker Registration");

        btnRegister = (Button) findViewById(R.id.btn_register);
        spinnerCertificationCompany = (Spinner) findViewById(R.id.spinner_certification_company);
        edtCertificationId = (EditText) findViewById(R.id.edt_certification_account_id);

        btnRegister.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this,
                R.array.certification_company_array,
                android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCertificationCompany.setAdapter(adapterType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                name = getIntent().getStringExtra(KEY.NAME_SEEKER_KEY);
                password = getIntent().getStringExtra(KEY.PASSWORD_SEEKER_KEY);
                province = getIntent().getStringExtra(KEY.PROVINCE_SEEKER_KEY);
                country = getIntent().getStringExtra(KEY.COUNTRY_SEEKER_KEY);
                email = getIntent().getStringExtra(KEY.EMAIL_SEEKER_KEY);
                certificationId = edtCertificationId.getText().toString();
                certificationCompany = spinnerCertificationCompany.getSelectedItem().toString();
                phone = getIntent().getStringExtra(KEY.PHONE_SEEKER_KEY);
                website = getIntent().getStringExtra(KEY.WEBSITE_SEEKER_KEY);
                linkedin = getIntent().getStringExtra(KEY.LINKEDIN_SEEKER_KEY);
                github = getIntent().getStringExtra(KEY.GITHUB_SEEKER_KEY);
                birthdayPlace = getIntent().getStringExtra(KEY.BIRTHDAY_PLACE_SEEKER_KEY);
                birthday = getIntent().getStringExtra(KEY.BIRTHDAY_SEEKER_KEY);
                lastEducation = getIntent().getStringExtra(KEY.LAST_EDUCATION_SEEKER_KEY);

                Intent i = new Intent(getApplicationContext(), CompleteSeekerWorkActivity.class);
                i.putExtra(KEY.NAME_SEEKER_KEY, name);
                i.putExtra(KEY.PASSWORD_SEEKER_KEY, password);
                i.putExtra(KEY.PROVINCE_SEEKER_KEY, province);
                i.putExtra(KEY.COUNTRY_SEEKER_KEY, country);
                i.putExtra(KEY.EMAIL_SEEKER_KEY, email);
                i.putExtra(KEY.PHONE_SEEKER_KEY, phone);
                i.putExtra(KEY.WEBSITE_SEEKER_KEY, website);
                i.putExtra(KEY.LINKEDIN_SEEKER_KEY, linkedin);
                i.putExtra(KEY.GITHUB_SEEKER_KEY, github);
                i.putExtra(KEY.BIRTHDAY_PLACE_SEEKER_KEY, birthdayPlace);
                i.putExtra(KEY.LAST_EDUCATION_SEEKER_KEY, lastEducation);
                i.putExtra(KEY.BIRTHDAY_SEEKER_KEY, birthday);
                i.putExtra(KEY.CERTIFICATION_COMPANY_SEEKER_KEY, certificationCompany);
                i.putExtra(KEY.CERTIFICATION_ID_SEEKER_KEY, certificationId);
                startActivity(i);
                break;
        }
    }
}
