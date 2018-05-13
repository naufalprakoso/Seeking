package com.njy.seeking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ExamActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnBegin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        btnBegin = (Button) findViewById(R.id.btn_begin);

        btnBegin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_begin:
                Toast.makeText(this, "Getting exam question", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
