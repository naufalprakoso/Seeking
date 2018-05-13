package com.njy.seeking.company;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class VacancyDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtLocation, txtSalary, txtSeatsLeft,
            txtType, txtExperience, txtLanguage, txtCertification,
            txtAdditional, txtJobDescription;
    private Button btnApplicantList;

    String position, location, salary, seatLeft, type,
            experience, language, certification, additional, jobDescription, vacancyId, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail);

        txtLocation = (TextView) findViewById(R.id.txt_location);
        txtSalary = (TextView) findViewById(R.id.txt_salary);
        txtSeatsLeft = (TextView) findViewById(R.id.txt_seat);
        txtType = (TextView) findViewById(R.id.txt_type);
        txtExperience = (TextView) findViewById(R.id.txt_experience);
        txtLanguage = (TextView) findViewById(R.id.txt_language);
        txtCertification = (TextView) findViewById(R.id.txt_certification);
        txtAdditional = (TextView) findViewById(R.id.txt_additional);
        txtJobDescription = (TextView) findViewById(R.id.txt_job_description);
        btnApplicantList = (Button) findViewById(R.id.btn_applicant_list);

        btnApplicantList.setOnClickListener(this);

        position = getIntent().getStringExtra(KEY.POSITION_KEY);
        location = getIntent().getStringExtra(KEY.LOCATION_KEY);
        salary = getIntent().getStringExtra(KEY.SALARY_KEY);
        seatLeft = getIntent().getStringExtra(KEY.SEAT_LEFT_KEY);
        type = getIntent().getStringExtra(KEY.TYPE_KEY);
        experience = getIntent().getStringExtra(KEY.EXPERIENCE_KEY);
        language = getIntent().getStringExtra(KEY.LANGUAGE_KEY);
        certification = getIntent().getStringExtra(KEY.CERTIFICATION_KEY);
        additional = getIntent().getStringExtra(KEY.ADDITIONAL_KEY);
        jobDescription = getIntent().getStringExtra(KEY.JOB_DESCRIPTION_KEY);
        vacancyId = getIntent().getStringExtra(KEY.VACANCY_ID_KEY);
        name = getIntent().getStringExtra(KEY.NAME_GET_COMPANY_KEY);

        setTitle(position);

        txtLocation.setText(location);
        txtSalary.setText(salary);
        txtSeatsLeft.setText(seatLeft);
        txtType.setText(type);
        txtExperience.setText(experience);
        txtLanguage.setText(language);
        txtCertification.setText(certification);
        txtAdditional.setText(additional);
        txtJobDescription.setText(jobDescription);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_applicant_list:
                Intent i = new Intent(getApplicationContext(), ApplicantActivity.class);
                i.putExtra(KEY.VACANCY_ID_KEY, vacancyId);
                i.putExtra(KEY.NAME_GET_COMPANY_KEY, name);
                startActivity(i);
                break;
        }
    }
}
