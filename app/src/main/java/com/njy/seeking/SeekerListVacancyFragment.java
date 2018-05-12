package com.njy.seeking;

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
import com.njy.seeking.adapter.VacancySeekerAdapter;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Vacancy;
import com.njy.seeking.seeker.SeekerHomeActivity;

import java.util.ArrayList;

public class SeekerListVacancyFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Vacancy> vacancies;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    VacancySeekerAdapter adapter;
    String companyName;

    SharedPreferences sharedPreferences;

    public SeekerListVacancyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seeker_list_vacancy, container, false);

        sharedPreferences = getActivity().getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);

        companyName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        vacancies = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("company").child(companyName + "").getChildren()){
                    Vacancy vacancy  = dataSnapshot.getValue(Vacancy.class);
                    vacancies.add(vacancy);
                }

                adapter = new VacancySeekerAdapter(getActivity(), vacancies);
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
