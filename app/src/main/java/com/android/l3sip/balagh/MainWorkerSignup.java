package com.android.l3sip.balagh;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainWorkerSignup extends AppCompatActivity {
    EditText wsignup_username,wsignup_email,wsignup_password,wcsignup_password;
    Button wsignup_button;
    TextView textView;


    String emailPattren = "[A-zA-Z0-9._-]+@[a-z]+\\.+[A-Z]+";
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Toolbar toolbar;
    private String Tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_worker_signup);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
     //   getSupportActionBar().setDisplayShowTitleEnabled(false);



        wsignup_username=findViewById(R.id.wsignup_username);
        wsignup_email=findViewById(R.id. wsignup_email);
        wsignup_password=findViewById(R.id.wsignup_password);
        wcsignup_password=findViewById(R.id. wcsignup_password);
        wsignup_button=findViewById(R.id.wsignup_button);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        textView=findViewById(R.id.haveaccaount1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainWorkerSignup.this,MainWorkerLogin.class));
            }
        });

        wsignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String username =  wsignup_username.getText().toString();
        String email = wsignup_email.getText().toString();
        String password = wsignup_password.getText().toString();
        String confirmpassword = wcsignup_password.getText().toString();

        if (username.isEmpty()) {
            wsignup_username.setError("Enter a username");
            Toast.makeText(this, "enter usarname", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()||email.matches(emailPattren)) {
            wsignup_email.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            wsignup_password.setError("Enter propre password");
        } else if (confirmpassword.isEmpty()||!password.equals(confirmpassword)) {
            wcsignup_password.setError("password ont match both field");
        } else {
            progressDialog.setMessage("Please wait while registration ...");
            progressDialog.setTitle("regisration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();




            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextAcvtivity();
                        Toast.makeText(MainWorkerSignup.this, "Registration secsesful", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainWorkerSignup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    private void sendUserToNextAcvtivity() {
        Intent intent=new Intent(MainWorkerSignup.this, MainActivityUntinHPWorker.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}