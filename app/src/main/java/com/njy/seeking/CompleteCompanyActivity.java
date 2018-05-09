package com.njy.seeking;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class CompleteCompanyActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegister, btnFindAddress;
    private EditText edtPhone, edtWebsite, edtDesc;
    private ImageView imgCompany;
    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_company);

        setTitle(getString(R.string.titleCompanyRegistration));

        btnFindAddress = (Button) findViewById(R.id.btn_find_address);
        btnRegister = (Button) findViewById(R.id.btn_register);
        imgCompany = (ImageView) findViewById(R.id.img_company);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtWebsite = (EditText) findViewById(R.id.edt_website);
        edtDesc = (EditText) findViewById(R.id.edt_description);

        btnFindAddress.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        imgCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_company:
                Intent iImg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iImg, 0);
                break;
            case R.id.btn_find_address:
                Intent iAddress = new Intent(getApplicationContext(), CompanyaAddressActivity.class);
                startActivity(iAddress);
                break;
            case R.id.btn_register:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
            imgPath = targetUri.toString();

            Bitmap bitmap;
            try{
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imgCompany.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
