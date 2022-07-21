package com.example.quizm_acompletequizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    RecyclerView mqueslist;
    ArrayList<ContestData> mcontestdata = new ArrayList<>();
    ArrayList<ArrayList<Boolean>> mchecklist = new ArrayList<ArrayList<Boolean>>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mqueslist = findViewById(R.id.quiz_rv);

        mcontestdata = (ArrayList<ContestData>) getIntent().getExtras().getSerializable("list");

        for (int i = 0; i < mcontestdata.size(); i++){
            ContestData t = mcontestdata.get(i);
            ArrayList<Boolean> tt = new ArrayList<>();
            for(int j = 0; j < t.getAns().size(); j++){
                tt.add(t.getAns().get(j));
            }
            mchecklist.add(tt);
        }

        QuizAdapter mquizAdapter = new QuizAdapter(mcontestdata);
        mqueslist.setAdapter(mquizAdapter);
        mqueslist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }


    public void showResult(View view) {

        try {
            int ans = 0;

            for(int i = 0; i < QuizAdapter.questionlist.size(); i++) {
                ContestData tmp = QuizAdapter.questionlist.get(i);
                //Toast.makeText(getApplicationContext(), tmp.getAns().toString().)
                ArrayList<Boolean> an = new ArrayList<>();
                ArrayList<Boolean> cl = new ArrayList<>();
                int cnt = 0;
                for (int j = 0; j < tmp.getAns().size(); j++) {
                    an.add(tmp.getAns().get(j));
                    cl.add(mchecklist.get(i).get(j));
                    if(an.get(j).equals(cl.get(j)))cnt++;
                }
                if(cnt == tmp.getAns().size())ans++;
            }
            String score = ans + "/" + mcontestdata.size();
//            Toast.makeText(getApplicationContext(), "Result = " + score, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }

}
