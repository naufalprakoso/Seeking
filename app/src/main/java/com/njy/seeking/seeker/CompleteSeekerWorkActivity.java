package com.njy.seeking.seeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class CompleteSeekerWorkActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;

    String name, password, province, country, email,
            phone, website, linkedin, github, birthdayPlace, birthday, lastEducation,
            certificationId, certificationCompany,
            interested, project, workPeriod, lastCompany;

    private EditText edtInterested, edtProject, edtWorkPeriod, edtLastCompany;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_seeker_work);

        btnRegister = (Button) findViewById(R.id.btn_register);
        edtInterested = (EditText) findViewById(R.id.edt_interested);
        edtProject = (EditText) findViewById(R.id.edt_project);
        edtWorkPeriod = (EditText) findViewById(R.id.edt_work_period);
        edtLastCompany = (EditText) findViewById(R.id.edt_last_company);

        btnRegister.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        mEdit = sharedPreferences.edit();
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
                certificationId = getIntent().getStringExtra(KEY.CERTIFICATION_ID_SEEKER_KEY);
                certificationCompany = getIntent().getStringExtra(KEY.CERTIFICATION_COMPANY_SEEKER_KEY);
                phone = getIntent().getStringExtra(KEY.PHONE_SEEKER_KEY);
                website = getIntent().getStringExtra(KEY.WEBSITE_SEEKER_KEY);
                linkedin = getIntent().getStringExtra(KEY.LINKEDIN_SEEKER_KEY);
                github = getIntent().getStringExtra(KEY.GITHUB_SEEKER_KEY);
                birthdayPlace = getIntent().getStringExtra(KEY.BIRTHDAY_PLACE_SEEKER_KEY);
                birthday = getIntent().getStringExtra(KEY.BIRTHDAY_SEEKER_KEY);
                lastEducation = getIntent().getStringExtra(KEY.LAST_EDUCATION_SEEKER_KEY);

                interested = edtInterested.getText().toString();
                project = edtProject.getText().toString();
                workPeriod = edtWorkPeriod.getText().toString();
                lastCompany = edtLastCompany.getText().toString();

                mEdit.putString(KEY.NAME_SEEKER_KEY, name);
                mEdit.putString(KEY.PASSWORD_SEEKER_KEY, password);
                mEdit.putString(KEY.PROVINCE_SEEKER_KEY, province);
                mEdit.putString(KEY.COUNTRY_SEEKER_KEY, country);
                mEdit.putString(KEY.EMAIL_SEEKER_KEY, email);
                mEdit.putString(KEY.CERTIFICATION_ID_SEEKER_KEY, certificationId);
                mEdit.putString(KEY.CERTIFICATION_COMPANY_SEEKER_KEY, certificationCompany);
                mEdit.putString(KEY.PHONE_SEEKER_KEY, phone);
                mEdit.putString(KEY.WEBSITE_SEEKER_KEY, website);
                mEdit.putString(KEY.LINKEDIN_SEEKER_KEY, linkedin);
                mEdit.putString(KEY.GITHUB_SEEKER_KEY, github);
                mEdit.putString(KEY.BIRTHDAY_PLACE_SEEKER_KEY, birthdayPlace);
                mEdit.putString(KEY.BIRTHDAY_SEEKER_KEY, birthday);
                mEdit.putString(KEY.LAST_EDUCATION_SEEKER_KEY, lastEducation);
                mEdit.putString(KEY.INTERESTED_SEEKER_KEY, interested);
                mEdit.putString(KEY.PROJECT_SEEKER_KEY, project);
                mEdit.putString(KEY.WORK_PERIOD_SEEKER_KEY, workPeriod);
                mEdit.putString(KEY.LAST_COMPANY_SEEKER_KEY, lastCompany);
                mEdit.commit();

                Intent i = new Intent(getApplicationContext(), LoginJobSeekerActivity.class);
                startActivity(i);
                finish();

                Toast.makeText(this, "Register Successful", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
