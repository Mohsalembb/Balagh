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

public class MainWorkerLogin extends AppCompatActivity {

    EditText wemail, wpassword;
    Button wloginButton;

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
        setContentView(R.layout.activity_main_worker_login);
       // getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        wemail=findViewById(R.id.wsignup_email);

        wpassword = findViewById(R.id.wsignup_password);
        wloginButton = findViewById(R.id.wloginButton);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        textView=findViewById(R.id.textView10);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainWorkerLogin.this, MainWorkerSignup.class));
            }
        });



        wloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        });


    }

    private void perforLogin() {
        String email = wemail.getText().toString();

        String password = wpassword.getText().toString();



        if (email.isEmpty()||email.matches(emailPattren)) {
            wemail.setError("Enter Correct Email");} else if (password.isEmpty() || password.length() < 6) {
            wpassword.setError("Enter propre password");
        } else {
            progressDialog.setMessage("Please wait while Login ...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextAcvtivity();
                        Toast.makeText(MainWorkerLogin.this, "Login secsesful", Toast.LENGTH_SHORT).show();

                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainWorkerLogin.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void sendUserToNextAcvtivity() {
        Intent intent=new Intent(MainWorkerLogin.this, MainActivityUntinHPWorker.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}