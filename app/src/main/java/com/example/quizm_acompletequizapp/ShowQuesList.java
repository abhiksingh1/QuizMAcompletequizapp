package com.example.quizm_acompletequizapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class ShowQuesList extends AppCompatActivity {

    RecyclerView mqueslist;
    ArrayList<ContestData> mcontestdata = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_ques_list);

        mqueslist = findViewById(R.id.showqueslist_rv);


        mcontestdata = (ArrayList<ContestData>)getIntent().getExtras().getSerializable("list");

        ShowQuesListAdapter mquestionlistadapter = new ShowQuesListAdapter(mcontestdata);
        mqueslist.setAdapter(mquestionlistadapter);
        mqueslist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }
}
