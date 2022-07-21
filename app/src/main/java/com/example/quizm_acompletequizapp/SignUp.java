package com.example.quizm_acompletequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    TextView mtv;
    EditText memail, mpasswd, mcfpasswd;
    Button msignup;
    ProgressBar mpbar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mtv = findViewById(R.id.signup_tv_signin);
        memail = findViewById(R.id.signup_edt_email);
        mpasswd = findViewById(R.id.signup_edt_passwd);
        mcfpasswd = findViewById(R.id.signup_edt_cfpasswd);
        msignup = findViewById(R.id.signup_btn_signup);
        mpbar = findViewById(R.id.signup_pbar);
        mAuth = FirebaseAuth.getInstance();

        mpbar.setVisibility(View.GONE);

        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        });

        msignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(SignUp.this, Home.class);
            startActivity(intent);
        }
    }

    private void signupUser() {
        mpbar.setVisibility(View.VISIBLE);
        String email, password, cfpassword;
        email = memail.getText().toString();
        password = mpasswd.getText().toString();
        cfpassword = mcfpasswd.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(cfpassword)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                    mpbar.setVisibility(View.GONE);
                    Intent intent = new Intent(SignUp.this, SignIn.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again", Toast.LENGTH_LONG).show();
//                    Toast.makeText(getApplicationContext(), "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    mpbar.setVisibility(View.GONE);
                }
            }
        });
    }

}