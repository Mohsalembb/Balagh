package com.android.l3sip.balagh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class MainUserSignup extends AppCompatActivity {
    EditText usignup_username,usignup_email,usignup_password,ucsignup_password;
    Button usignup_button;
    TextView textView1;


    String emailPattren = "[A-zA-Z0-9._-]+@[a-z]+\\.+[A-Z]+";
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private String Tag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_signup);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.Blue)));



        usignup_username=findViewById(R.id.usignup_username);
        usignup_email=findViewById(R.id. usignup_email);
        usignup_password=findViewById(R.id.usignup_password);
        ucsignup_password=findViewById(R.id. ucsignup_password);
        usignup_button=findViewById(R.id.usignup_button);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser= mAuth.getCurrentUser();
        textView1=findViewById(R.id.haveaccaount);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainUserSignup.this,MainUserLogin.class));
            }
        });

        usignup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PerforAuth();
            }
        });
    }

    private void PerforAuth() {
        String username =  usignup_username.getText().toString();
        String email = usignup_email.getText().toString();
        String password =usignup_password.getText().toString();
        String confirmpassword = ucsignup_password.getText().toString();

        if (username.isEmpty()) {
            usignup_username.setError("Enter a username");
            Toast.makeText(this, "enter usarname", Toast.LENGTH_SHORT).show();
        } else if (email.isEmpty()||email.matches(emailPattren)) {
            usignup_email.setError("Enter Correct Email");
        } else if (password.isEmpty() || password.length() < 6) {
            usignup_password.setError("Enter propre password");
        } else if (confirmpassword.isEmpty()||!password.equals(confirmpassword)) {
            ucsignup_password.setError("password ont match both field");
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
                        Toast.makeText(MainUserSignup.this, "Registration secsesful", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(MainUserSignup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }

    private void sendUserToNextAcvtivity() {
        Intent intent=new Intent(MainUserSignup.this, MainActivityUntilHpUser.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }






    }