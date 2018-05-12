package com.njy.seeking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.njy.seeking.data.KEY;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCompany, btnSeeker;
    SharedPreferences sharedPreferences;
    String roleLogin;
    boolean isFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompany = (Button) findViewById(R.id.btn_company);
        btnSeeker = (Button) findViewById(R.id.btn_seeker);

        btnCompany.setOnClickListener(this);
        btnSeeker.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(KEY.SEEKING_KEY, MODE_PRIVATE);
        isFirstTime = sharedPreferences.getBoolean(KEY.FIRST_LOGIN_KEY, false);
        roleLogin = sharedPreferences.getString(KEY.ROLE_KEY, null);

        if (isFirstTime && roleLogin.equals("Company")){
            Intent i = new Intent(getApplicationContext(), CompanyHomeActivity.class);
            startActivity(i);
        }else if (isFirstTime && roleLogin.equals("Seeker")){
//            Intent i = new Intent(getApplicationContext(), CompanyHomeActivity.class);
//            startActivity(i);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_company:
                Intent i = new Intent(getApplicationContext(), LoginCompanyActivity.class);
                startActivity(i);
                break;
            case R.id.btn_seeker:
                break;
        }
    }
}
