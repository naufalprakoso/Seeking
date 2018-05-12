package com.njy.seeking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.njy.seeking.data.KEY;

public class CreateVacancyActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinnerType;
    private EditText edtPosition, edtLocation, edtSeatLeft,
            edtSalary, edtExperience, edtLanguage, edtCertification, edtAdditional, edtJobDescription;
    private Button btnCreate;

    String position, location, seatLeft, salary, experience, language, certification, additional, jobDescription, type;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;
    String getName;
    int getDataKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vacancy);

        setTitle("Create Job Vacancy");

        spinnerType = (Spinner) findViewById(R.id.sp_type);
        edtPosition = (EditText) findViewById(R.id.edt_position);
        edtLocation = (EditText) findViewById(R.id.edt_location);
        edtSeatLeft = (EditText) findViewById(R.id.edt_seat_left);
        edtSalary = (EditText) findViewById(R.id.edt_salary);
        edtExperience = (EditText) findViewById(R.id.edt_experience_years);
        edtLanguage = (EditText) findViewById(R.id.edt_language);
        edtCertification = (EditText) findViewById(R.id.edt_certification);
        edtAdditional = (EditText) findViewById(R.id.edt_additional);
        edtJobDescription = (EditText) findViewById(R.id.edt_description);
        btnCreate = (Button) findViewById(R.id.btn_create);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, MODE_PRIVATE);
        mEdit = sharedPreferences.edit();
        getName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);
        getDataKey = sharedPreferences.getInt(KEY.DATA_KEY, 0);

        firebaseDatabase = FirebaseDatabase.getInstance();

        btnCreate.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(
                this,
                R.array.type_array,
                android.R.layout.simple_spinner_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_create:
                position = edtPosition.getText().toString();
                location = edtLocation.getText().toString();
                seatLeft = edtSeatLeft.getText().toString();
                salary = edtSalary.getText().toString();
                experience = edtExperience.getText().toString();
                language = edtLanguage.getText().toString();
                certification = edtCertification.getText().toString();
                additional = edtAdditional.getText().toString();
                jobDescription = edtJobDescription.getText().toString();
                type = spinnerType.getSelectedItem().toString();

                databaseReference = firebaseDatabase.getReference();

                databaseReference.child("vacancy" + getName + getDataKey).setValue(position);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(type);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(location);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(seatLeft);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(salary);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(experience);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(language);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(certification);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(additional);
                databaseReference.child("vacancy" + getName + getDataKey).setValue(jobDescription);

                mEdit.putInt(KEY.DATA_KEY, getDataKey++);
                mEdit.commit();

                Toast.makeText(this, "Create successful", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), CompanyHomeActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
