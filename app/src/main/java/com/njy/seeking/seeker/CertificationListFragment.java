package com.njy.seeking.seeker;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njy.seeking.R;
import com.njy.seeking.adapter.CertificationAdapter;
import com.njy.seeking.adapter.VacancySeekerAdapter;
import com.njy.seeking.model.Certification;
import com.njy.seeking.model.Vacancy;

import java.util.ArrayList;

public class CertificationListFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Certification> certifications;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    CertificationAdapter adapter;

    public CertificationListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_certification_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        certifications = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("certification").getChildren()){
                    Certification certification  = dataSnapshot.getValue(Certification.class);
                    certifications.add(certification);
                }

                adapter = new CertificationAdapter(getActivity(), certifications);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        return view;
    }
}
