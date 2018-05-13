package com.njy.seeking.seeker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.njy.seeking.R;
import com.njy.seeking.data.KEY;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CertificationDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtOrganize, txtPrice, txtTestDescription, txtVenue, txtLanguage;

    String organize, price, name, testDescription, venue, language, testExam,
            getPhone;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    SharedPreferences sharedPreferences;

    Calendar calendar;
    DatePickerDialog.OnDateSetListener date;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        txtOrganize = (TextView) findViewById(R.id.txt_organize);
        txtPrice = (TextView) findViewById(R.id.txt_price);
        txtTestDescription = (TextView) findViewById(R.id.txt_test_description);
        txtVenue = (TextView) findViewById(R.id.txt_venue);
        txtLanguage = (TextView) findViewById(R.id.txt_language);

        name = getIntent().getStringExtra(KEY.NAME_CERTIFICATION_KEY);
        organize = getIntent().getStringExtra(KEY.ORGANIZE_CERTIFICATION_KEY);
        price = getIntent().getStringExtra(KEY.PRICE_CERTIFICATION_KEY);
        testDescription = getIntent().getStringExtra(KEY.TEST_DESCRIPTION_CERTIFICATION_KEY);
        venue = getIntent().getStringExtra(KEY.VENUE_CERTIFICATION_KEY);
        language = getIntent().getStringExtra(KEY.LANGUAGE_CERTIFICATION_KEY);

        fab.setOnClickListener(this);

        setTitle(name + "");

        txtOrganize.setText(organize);
        txtPrice.setText(price);
        txtTestDescription.setText(testDescription);
        txtVenue.setText(venue);
        txtLanguage.setText(language);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);

        getPhone = sharedPreferences.getString(KEY.PHONE_SEEKER_KEY, null);

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

    private void updateLabel(){
        String myFormat  = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(myFormat, Locale.US);

        testExam = simpleDateFormat.format(calendar.getTime());

        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("name").setValue(name + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("organize").setValue(organize + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("price").setValue(price + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("testdescription").setValue(testDescription + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("venue").setValue(venue + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("language").setValue(language + "");
        databaseReference.child("users").child(getPhone + "").child("certifications")
                .child(name + "").child("testexam").setValue(testExam + "");

        Toast.makeText(CertificationDetailActivity.this, "Successful to take certificate exam!", Toast.LENGTH_SHORT).show();

        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                new DatePickerDialog(this, date,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }
    }
}
