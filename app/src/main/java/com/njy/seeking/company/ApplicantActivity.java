package com.njy.seeking.company;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njy.seeking.R;
import com.njy.seeking.adapter.ApplicantAdapter;
import com.njy.seeking.adapter.CertificationAdapter;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Applicant;
import com.njy.seeking.model.Certification;

import java.util.ArrayList;

public class ApplicantActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Applicant> applicants;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    ApplicantAdapter adapter;

    String getName, getVacancyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        applicants = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        getName = getIntent().getStringExtra(KEY.NAME_GET_COMPANY_KEY);
        getVacancyId = getIntent().getStringExtra(KEY.VACANCY_ID_KEY);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("company").child(getName + "")
                        .child(getVacancyId + "").child("applicants").getChildren()){
                    Applicant applicant  = dataSnapshot.getValue(Applicant.class);
                    applicants.add(applicant);
                }

                adapter = new ApplicantAdapter(ApplicantActivity.this, applicants);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
