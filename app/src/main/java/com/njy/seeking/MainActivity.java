package com.njy.seeking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCompany, btnSeeker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCompany = (Button) findViewById(R.id.btn_company);
        btnSeeker = (Button) findViewById(R.id.btn_seeker);

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
                break;
        }
    }
}
