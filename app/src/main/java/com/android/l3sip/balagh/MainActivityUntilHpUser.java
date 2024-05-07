package com.android.l3sip.balagh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivityUntilHpUser extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_untilhp_user);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityUntilHpUser.this, MainActivityHomePageUser.class));
            }
        });

    }
}