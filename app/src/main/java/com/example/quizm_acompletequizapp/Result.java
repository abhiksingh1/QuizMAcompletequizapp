package com.example.quizm_acompletequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Result extends AppCompatActivity {
    TextView mresultscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mresultscore = findViewById(R.id.result_score);

        String score = getIntent().getStringExtra("score");
        mresultscore.setText(score);
    }


    public void home(View view) {
        Intent intent = new Intent(Result.this, Home.class);
        startActivity(intent);
        finish();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Result.this, SignIn.class);
        startActivity(intent);
        finish();
    }

}