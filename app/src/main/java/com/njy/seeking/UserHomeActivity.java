package com.njy.seeking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njy.seeking.adapter.VacancySeekerAdapter;
import com.njy.seeking.company.CompanyHomeActivity;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Vacancy;
import com.njy.seeking.seeker.SeekerHomeActivity;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;
    ArrayList<Vacancy> vacancies;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    VacancySeekerAdapter adapter;
    String companyName;

    SharedPreferences sharedPreferences;
    String roleLogin;
    boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(KEY.FIRST_LOGIN_KEY, false);
        roleLogin = sharedPreferences.getString(KEY.ROLE_KEY, null);

        if (isFirstTime && roleLogin.equals("Company")){
            Intent i = new Intent(getApplicationContext(), CompanyHomeActivity.class);
            startActivity(i);
        }else if (isFirstTime && roleLogin.equals("Seeker")){
            Intent i = new Intent(getApplicationContext(), SeekerHomeActivity.class);
            startActivity(i);
        }

        companyName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);

        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        vacancies = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.child("company").getChildren()){
                    for (DataSnapshot endSnapshot : dataSnapshot.getChildren()){
                        Vacancy vacancy  = endSnapshot.getValue(Vacancy.class);
                        vacancies.add(vacancy);
                    }
                }

                adapter = new VacancySeekerAdapter(getApplicationContext(), vacancies);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_list_vacancy) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_sign_up) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
