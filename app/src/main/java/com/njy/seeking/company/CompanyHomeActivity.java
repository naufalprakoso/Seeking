package com.njy.seeking.company;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.njy.seeking.MainActivity;
import com.njy.seeking.R;
import com.njy.seeking.UserHomeActivity;
import com.njy.seeking.adapter.VacancyAdapter;
import com.njy.seeking.data.KEY;
import com.njy.seeking.model.Vacancy;

import java.util.ArrayList;

public class CompanyHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    boolean isFirstTime = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;
    String companyName;

    private ImageView imgProfile;
    private TextView txtUserName, txtUserEmail;

    String userName, userEmail;

    private RecyclerView recyclerView;
    ArrayList<Vacancy> vacancies;

    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    VacancyAdapter adapter;

    DialogInterface.OnClickListener dialogClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateVacancyActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, Context.MODE_PRIVATE);
        mEdit = sharedPreferences.edit();
        isFirstTime = sharedPreferences.getBoolean(KEY.FIRST_LOGIN_KEY, false);

        companyName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);

        userName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);
        userEmail = sharedPreferences.getString(KEY.EMAIL_COMPANY_KEY, null);

        View headerView = navigationView.getHeaderView(0);

        imgProfile = (ImageView) headerView.findViewById(R.id.img_user);
        txtUserName = (TextView) headerView.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView) headerView.findViewById(R.id.txt_user_email);

        imgProfile.setOnClickListener(this);

        if (!isFirstTime){
            Intent i = new Intent(getApplicationContext(), LoginCompanyActivity.class);
            startActivity(i);
        }else{
            txtUserName.setText(userName);
            txtUserEmail.setText(userEmail);
        }

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
                for (DataSnapshot dataSnapshot : snapshot.child("company").child(companyName + "").getChildren()){
                    Vacancy vacancy  = dataSnapshot.getValue(Vacancy.class);
                    vacancies.add(vacancy);
                }

                adapter = new VacancyAdapter(CompanyHomeActivity.this, vacancies);
                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        mEdit.putString(KEY.ROLE_KEY, null);
                        mEdit.putBoolean(KEY.FIRST_LOGIN_KEY, false);
                        mEdit.commit();

                        Intent i = new Intent(getApplicationContext(), UserHomeActivity.class);
                        startActivity(i);
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.company_home, menu);
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

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure to logout?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_user:
                Intent i = new Intent(getApplicationContext(), UpdateCompanyProfileActivity.class);
                startActivity(i);
                break;
        }
    }
}
