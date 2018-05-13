package com.njy.seeking.company;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

public class ApplicantDetailActivity extends AppCompatActivity {

    private TextView txtName, txtEmail, txtCertificationId,
            txtCertificationCompany, txtGithub, txtLinkedin, txtInterested,
            txtLastEducation, txtLastCompany, txtPhone, txtProject, txtWebsite, txtWorkPeriod;

    String name, email, certificationId, certificationCompany,
            github, linkedin, interested, lastEducation, lastCompany,
            phone, project, website, workPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant_detail);

        txtName = (TextView) findViewById(R.id.txt_name);
        txtEmail = (TextView) findViewById(R.id.txt_email);
        txtCertificationId= (TextView) findViewById(R.id.txt_certification_id);
        txtCertificationCompany = (TextView) findViewById(R.id.txt_certification_company);
        txtGithub = (TextView) findViewById(R.id.txt_github);
        txtLinkedin = (TextView) findViewById(R.id.txt_linkedin);
        txtInterested = (TextView) findViewById(R.id.txt_interested);
        txtLastEducation = (TextView) findViewById(R.id.txt_last_education);
        txtLastCompany = (TextView) findViewById(R.id.txt_last_company_name);
        txtPhone = (TextView) findViewById(R.id.txt_phone);
        txtProject = (TextView) findViewById(R.id.txt_project);
        txtWebsite = (TextView) findViewById(R.id.txt_website);
        txtWorkPeriod = (TextView) findViewById(R.id.txt_last_company_work_period);

        name = getIntent().getStringExtra(KEY.NAME_SEEKER_KEY);
        email = getIntent().getStringExtra(KEY.EMAIL_SEEKER_KEY);
        certificationId = getIntent().getStringExtra(KEY.CERTIFICATION_ID_SEEKER_KEY);
        certificationCompany = getIntent().getStringExtra(KEY.CERTIFICATION_COMPANY_SEEKER_KEY);
        github = getIntent().getStringExtra(KEY.GITHUB_SEEKER_KEY);
        linkedin = getIntent().getStringExtra(KEY.LINKEDIN_SEEKER_KEY);
        interested = getIntent().getStringExtra(KEY.INTERESTED_SEEKER_KEY);
        lastEducation = getIntent().getStringExtra(KEY.LAST_EDUCATION_SEEKER_KEY);
        lastCompany = getIntent().getStringExtra(KEY.LAST_COMPANY_SEEKER_KEY);
        phone = getIntent().getStringExtra(KEY.PHONE_SEEKER_KEY);
        project = getIntent().getStringExtra(KEY.PROJECT_SEEKER_KEY);
        website = getIntent().getStringExtra(KEY.WEBSITE_SEEKER_KEY);
        workPeriod = getIntent().getStringExtra(KEY.WORK_PERIOD_SEEKER_KEY);

        txtName.setText(name);
        txtEmail.setText(email);
        txtCertificationId.setText(certificationId);
        txtCertificationCompany.setText(certificationCompany);
        txtGithub.setText(github);
        txtLinkedin.setText(linkedin);
        txtInterested.setText(interested);
        txtLastEducation.setText(lastEducation);
        txtLastCompany.setText(lastCompany);
        txtPhone.setText(phone);
        txtProject.setText(project);
        txtWebsite.setText(website);
        txtWorkPeriod.setText(workPeriod);
    }
}
