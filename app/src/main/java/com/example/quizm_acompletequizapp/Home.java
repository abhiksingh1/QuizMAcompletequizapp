package com.example.quizm_acompletequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void createContest(View view) {

        Intent intent = new Intent(this, CreateContest.class);
        startActivity(intent);
        finish();
    }

    public void giveContest(View view) {

        Intent intent = new Intent(this, ShowCategory.class);
        startActivity(intent);
        finish();
    }
}