package com.android.l3sip.balagh;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivityHomePageUser extends AppCompatActivity {

    TextView btnPickImage1;
    ImageView imageView;
    ImageView image;
    ActivityResultLauncher<Intent> resultLauncher;
    Button abutton,erjonsbutton,nenrjonsbutton;
    EditText description,location;
    RadioButton radioButton,radioButton2;
    Uri uri;
    RadioGroup radio;
    RadioButton radioButton1;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page_user);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));
        abutton=findViewById(R.id.abutton);
        description=findViewById(R.id.description);
        location=findViewById(R.id.location);
        radioButton=findViewById(R.id.radioButton);
        radioButton2=findViewById(R.id.radioButton2);
        radio=findViewById(R.id.radio);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ID=radio.getCheckedRadioButtonId();
                radioButton1=findViewById(ID);

                String description1 =description.getText().toString();
                String location1 = location.getText().toString();
                String erjonsbutton1=radioButton1.getText().toString();
                //String nenrjonsbutton1="غير مستعجلة";
                Intent intent=new Intent(MainActivityHomePageUser.this,MainActivityThanks.class);
                intent.putExtra("keyerj",erjonsbutton1);
                intent.putExtra("keydesc",description1);
                intent.putExtra("keyloc",location1);
                intent.putExtra("keyimage",uri);
                startActivity(intent);


            }
        });





        btnPickImage1 = findViewById(R.id.btnPickImage1);
        imageView = findViewById(R.id.imageView);

        btnPickImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });



       }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && data!=null){
            uri=data.getData();
            imageView.setImageURI(uri);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}