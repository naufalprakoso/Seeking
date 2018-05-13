package com.njy.seeking.seeker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.njy.seeking.ExamActivity;
import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class VacancyDetailSeekerActivity extends AppCompatActivity {

    private TextView txtLocation, txtSalary, txtSeatsLeft,
            txtType, txtExperience, txtLanguage, txtCertification,
            txtAdditional, txtJobDescription,
            txtQualify, txtName, txtPosition,
            txtCompanyDescription;

    String position, location, salary, seatLeft, type,
            experience, language, certification, additional, jobDescription, vacancyId, name,
            getProject, getInterested, getEmail, getName, getCertificationCompany, getCertificationId,
            getPhone, getWebsite, getGithub, getLinkedin, getLastEducation, getWorkPeriod, getLastCompany,
            getCompanyDescription;

    boolean isLogin;

    int getDataApplicant;
    boolean getSubmited;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy_detail_seeker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        txtSalary = (TextView) findViewById(R.id.txt_salary);
        txtSeatsLeft = (TextView) findViewById(R.id.txt_seat);
        txtType = (TextView) findViewById(R.id.txt_type);
        txtExperience = (TextView) findViewById(R.id.txt_experience);
        txtLanguage = (TextView) findViewById(R.id.txt_language);
        txtCertification = (TextView) findViewById(R.id.txt_certification);
        txtAdditional = (TextView) findViewById(R.id.txt_additional);
        txtJobDescription = (TextView) findViewById(R.id.txt_job_description);
        txtQualify = (TextView) findViewById(R.id.txt_qualify);
        txtPosition = (TextView) findViewById(R.id.txt_position);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtCompanyDescription = (TextView) findViewById(R.id.txt_company_description);

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

        setTitle("Job Vacancy Detail");

        txtLocation.setText(location);
        txtSalary.setText(salary);
        txtSeatsLeft.setText(seatLeft);
        txtType.setText(type);
        txtExperience.setText(experience);
        txtLanguage.setText(language);
        txtCertification.setText(certification);
        txtAdditional.setText(additional);
        txtJobDescription.setText(jobDescription);
        txtPosition.setText(position);
        txtName.setText(name);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        mEdit = sharedPreferences.edit();

        getInterested = sharedPreferences.getString(KEY.INTERESTED_SEEKER_KEY, null);
        getProject = sharedPreferences.getString(KEY.PROJECT_SEEKER_KEY, null);
        getEmail = sharedPreferences.getString(KEY.EMAIL_SEEKER_KEY, null);
        getName = sharedPreferences.getString(KEY.NAME_SEEKER_KEY, null);
        getCertificationId = sharedPreferences.getString(KEY.CERTIFICATION_ID_SEEKER_KEY, null);
        getCertificationCompany = sharedPreferences.getString(KEY.CERTIFICATION_COMPANY_SEEKER_KEY, null);
        getPhone = sharedPreferences.getString(KEY.PHONE_SEEKER_KEY, null);
        getWebsite = sharedPreferences.getString(KEY.WEBSITE_SEEKER_KEY, null);
        getGithub = sharedPreferences.getString(KEY.GITHUB_SEEKER_KEY, null);
        getLinkedin = sharedPreferences.getString(KEY.LINKEDIN_SEEKER_KEY, null);
        getLastEducation = sharedPreferences.getString(KEY.LAST_EDUCATION_SEEKER_KEY, null);
        getWorkPeriod = sharedPreferences.getString(KEY.WORK_PERIOD_SEEKER_KEY, null);
        getLastCompany = sharedPreferences.getString(KEY.LAST_COMPANY_SEEKER_KEY, null);
        getCompanyDescription = sharedPreferences.getString(KEY.DESCRIPTION_COMPANY_KEY, null);

        txtCompanyDescription.setText(getCompanyDescription + "Perusahaan yang bergerak di bidang e-commerce");

        getDataApplicant = sharedPreferences.getInt(KEY.DATA_APPLICANT_KEY, 0);
        getSubmited = sharedPreferences.getBoolean(KEY.SUBMITED_KEY, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        if(getInterested == null){
            txtQualify.setText("You must login to apply this job");
            
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(VacancyDetailSeekerActivity.this, "Login to apply this job", Toast.LENGTH_SHORT).show();
                }
            });
        } else if (getInterested.contains("Android") || getProject.contains("Android")){
            isLogin = sharedPreferences.getBoolean(KEY.FIRST_LOGIN_KEY, false);
            if (!isLogin){
                txtQualify.setText("You must login to apply this job");
            }else{
                txtQualify.setText("You're qualified to apply this job");
            }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isLogin){
                        Toast.makeText(VacancyDetailSeekerActivity.this,
                                "You must login to apply this job", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(VacancyDetailSeekerActivity.this,
                                "Apply successful, wait for next email. Good Luck!", Toast.LENGTH_SHORT).show();

                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("name").setValue(getName);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("interested").setValue(getInterested);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("project").setValue(getProject);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("email").setValue(getEmail);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("certificationid").setValue(getCertificationId);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("certificationcompany").setValue(getCertificationCompany);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("phone").setValue(getPhone);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("website").setValue(getWebsite);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("github").setValue(getGithub);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("linkedin").setValue(getLinkedin);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("lasteducation").setValue(getLastEducation);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("workperiod").setValue(getWorkPeriod);
                        databaseReference.child("company").child(name + "").child(vacancyId + "").
                                child("applicants").child("applicant" + getDataApplicant).child("lastcompany").setValue(getLastCompany);

                        getDataApplicant += 1;
                        mEdit.putInt(KEY.DATA_APPLICANT_KEY, getDataApplicant);
                        mEdit.putBoolean(KEY.SUBMITED_KEY, true);
                        mEdit.commit();

                        finish();
                    }
                }
            });
        }else if(getSubmited){
            txtQualify.setText("Wait for your next email for this job vacancy, Good Luck!");

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(VacancyDetailSeekerActivity.this,
                            "Wait for your next email for this job vacancy, Good Luck!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            txtQualify.setText("You're not qualified to apply this job, take an exam to make yourself qualify. Click the button to take an exam.");

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(VacancyDetailSeekerActivity.this,
//                            "Process an exam", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(), ExamActivity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
    }

}
