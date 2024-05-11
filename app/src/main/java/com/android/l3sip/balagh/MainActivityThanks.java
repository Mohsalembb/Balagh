package com.android.l3sip.balagh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityThanks extends AppCompatActivity {

    TextView typeproblem1,description1,locaproblem1;
    ImageView imageView1;
    Intent intent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thanks);
      //  getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        typeproblem1=findViewById(R.id.typeproblem1);
        description1=findViewById(R.id.description1);
        locaproblem1=findViewById(R.id.locaproblem1);
        imageView1=findViewById(R.id.imageView1);

        intent =getIntent();
        Uri uri=(Uri) intent.getParcelableExtra("keyimage");
        String typeproblem2=getIntent().getStringExtra("keyerj");
        String descriptin2 =getIntent().getStringExtra("keydesc");
        String location2 =getIntent().getStringExtra("keyloc");
        imageView1.setImageURI(uri);
        description1.setText(descriptin2);
        locaproblem1.setText(location2);
        typeproblem1.setText(typeproblem2);



    }
}