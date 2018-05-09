package com.njy.seeking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompleteCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_company);

        setTitle(getString(R.string.titleCompanyRegistration));
    }
}
