package com.njy.seeking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.njy.seeking.adapter.VacancyAdapter;
import com.njy.seeking.data.KEY;
import com.njy.seeking.data.VacancyData;
import com.njy.seeking.model.Vacancy;

import java.util.ArrayList;

public class CompanyHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean isFirstTime = false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor mEdit;

    private ImageView imgProfile;
    private TextView txtUserName, txtUserEmail;

    String userName, userEmail;

    private RecyclerView recyclerView;
    ArrayList<Vacancy> vacancies;

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

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, MODE_PRIVATE);
        mEdit = sharedPreferences.edit();
        isFirstTime = sharedPreferences.getBoolean(KEY.FIRST_LOGIN_KEY, false);

        userName = sharedPreferences.getString(KEY.NAME_COMPANY_KEY, null);
        userEmail = sharedPreferences.getString(KEY.EMAIL_COMPANY_KEY, null);

        View headerView = navigationView.getHeaderView(0);

        imgProfile = (ImageView) headerView.findViewById(R.id.img_user);
        txtUserName = (TextView) headerView.findViewById(R.id.txt_user_name);
        txtUserEmail = (TextView) headerView.findViewById(R.id.txt_user_email);

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
        vacancies.addAll(VacancyData.getList());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        VacancyAdapter vacancyAdapter = new VacancyAdapter(this);
        vacancyAdapter.setList(vacancies);

        recyclerView.setAdapter(vacancyAdapter);
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
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();

            mEdit.putString(KEY.ROLE_KEY, null);
            mEdit.putBoolean(KEY.FIRST_LOGIN_KEY, false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
