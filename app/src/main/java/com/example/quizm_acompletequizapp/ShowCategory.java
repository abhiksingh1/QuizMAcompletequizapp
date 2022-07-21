package com.example.quizm_acompletequizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowCategory extends AppCompatActivity {

    RecyclerView recyclerView;
    //String[] categorylist = { "General", "Science", "Mathematics", "Biology", "Physics", "Chemistry"};
    FirebaseFirestore db;
    ArrayList<String> categorylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_category);


        categorylist.add("General");
        categorylist.add("Science");
        categorylist.add("Mathematics");
        categorylist.add("Biology");
        categorylist.add("Physics");
        categorylist.add("Chemistry");


//        Intent intent = new Intent();

//        categorylist.add(intent.getStringExtra("a"));
//        categorylist.add(intent.getStringExtra("b"));
//        categorylist.add(intent.getStringExtra("c"));
//        categorylist.add(intent.getStringExtra("d"));
//        categorylist.add(intent.getStringExtra("e"));
//        categorylist.add(intent.getStringExtra("f"));



        recyclerView = findViewById(R.id.showcategory_rv_categorylist);
        ShowCategoryAdapter mAdapter = new ShowCategoryAdapter(categorylist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
}