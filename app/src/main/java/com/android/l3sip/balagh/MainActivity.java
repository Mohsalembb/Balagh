package com.android.l3sip.balagh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));
    }

    public void ubutton (View view){
        Intent myintent1 = new Intent(this, MainUserLogin.class);
        startActivity(myintent1);
    }

    public void wbutton (View view){
        Intent myintent2 = new Intent(this, MainWorkerLogin.class);
        startActivity(myintent2);
    }

}