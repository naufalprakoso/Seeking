package com.njy.seeking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.njy.seeking.R;
import com.njy.seeking.company.CompanyHomeActivity;
import com.njy.seeking.company.LoginCompanyActivity;
import com.njy.seeking.data.KEY;
import com.njy.seeking.seeker.LoginJobSeekerActivity;
import com.njy.seeking.seeker.SeekerHomeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btnCompany, btnSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompany = (LinearLayout) findViewById(R.id.btn_company);
        btnSeeker = (LinearLayout) findViewById(R.id.btn_seeker);

        btnCompany.setOnClickListener(this);
        btnSeeker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_company:
                Intent i = new Intent(getApplicationContext(), LoginCompanyActivity.class);
                startActivity(i);
                break;
            case R.id.btn_seeker:
                Intent ia = new Intent(getApplicationContext(), LoginJobSeekerActivity.class);
                startActivity(ia);
                break;
        }
    }
}
