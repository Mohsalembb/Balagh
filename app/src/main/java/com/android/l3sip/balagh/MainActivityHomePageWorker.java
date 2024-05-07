package com.android.l3sip.balagh;

import static android.app.ProgressDialog.show;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivityHomePageWorker extends AppCompatActivity {

    View btnPickImage;
    ImageView imageView;
    ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page_worker);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));


        btnPickImage = findViewById(R.id.btnPickImage);
        imageView = findViewById(R.id.imageView);
        registerResult();
        btnPickImage.setOnClickListener(view -> PickImage());
    }

    private void PickImage() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);

    }





    private void registerResult() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            Uri imageUri = result.getData().getData();
                            imageView.setImageURI(imageUri);
                        } catch (Exception e) {
                            Toast.makeText(MainActivityHomePageWorker.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}