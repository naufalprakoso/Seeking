package com.njy.seeking.seeker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CompleteSeekerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private EditText edtPhone, edtWebsite, edtLinkedin,
            edtGithub, edtBirthdayPlace, edtLastEducation, edtBirthday;

    String name, password, province, country, email,
            phone, website, linkedin, github, birthdayPlace, birthday, lastEducation;

    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_seeker);

        setTitle("Job Seeker Registration");

        btnRegister = (Button) findViewById(R.id.btn_register);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtWebsite = (EditText) findViewById(R.id.edt_website);
        edtLinkedin = (EditText) findViewById(R.id.edt_linkedin);
        edtGithub = (EditText) findViewById(R.id.edt_github);
        edtBirthdayPlace = (EditText) findViewById(R.id.edt_birthday_place);
        edtLastEducation = (EditText) findViewById(R.id.edt_last_education);
        edtBirthday = (EditText) findViewById(R.id.edt_birthday);

        btnRegister.setOnClickListener(this);
        edtBirthday.setOnClickListener(this);

        calendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
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

                phone = edtPhone.getText().toString();
                website = edtWebsite.getText().toString();
                linkedin = edtLinkedin.getText().toString();
                github = edtGithub.getText().toString();
                birthdayPlace = edtBirthdayPlace.getText().toString();
                lastEducation = edtLastEducation.getText().toString();

                Intent i = new Intent(getApplicationContext(), CompleteSeekerCertificationActivity.class);
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
                startActivity(i);
                break;
            case R.id.edt_birthday:
                new DatePickerDialog(this, date,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }

    private void updateLabel(){
        String myFormat  = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);

        edtBirthday.setText(simpleDateFormat.format(calendar.getTime()));
        birthday = simpleDateFormat.format(calendar.getTime());
    }
}
