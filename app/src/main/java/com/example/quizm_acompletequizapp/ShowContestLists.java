package com.example.quizm_acompletequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowContestLists extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> contestList = new ArrayList<>();
    public static ArrayList<String> srcpath = new ArrayList<>();
    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contest_lists);

        recyclerView = findViewById(R.id.showcontestlists_rv_clists);
        contestList = (ArrayList<String>) getIntent().getExtras().getStringArrayList("contestlist");
        srcpath = (ArrayList<String>) getIntent().getExtras().getStringArrayList("path");
        category = getIntent().getExtras().getString("category");
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        ShowContestListsAdapter showContestListsAdapter = new ShowContestListsAdapter(contestList);
        recyclerView.setAdapter(showContestListsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    public static String getCategory() {
        return category;
    }

    public static ArrayList<String> getSrcpath() {
        return srcpath;
    }
}