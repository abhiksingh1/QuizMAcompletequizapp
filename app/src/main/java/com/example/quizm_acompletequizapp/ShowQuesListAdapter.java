package com.example.quizm_acompletequizapp;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowQuesListAdapter extends RecyclerView.Adapter<ShowQuesListAdapter.QuestionListView> {


    ArrayList<ContestData> questionlist = new ArrayList<>();

    public ShowQuesListAdapter(ArrayList<ContestData> questionlist) {
        this.questionlist = questionlist;
    }

    @NonNull
    @Override
    public QuestionListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mlayoutinflater = LayoutInflater.from(parent.getContext());
        View mview = mlayoutinflater.inflate(R.layout.row_question, parent, false);
        return new QuestionListView(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionListView holder, int position) {

        ContestData mcontestdata = questionlist.get(position);
        String questionname = mcontestdata.getQuestion();
        ArrayList<Boolean> mans = mcontestdata.getAns();
        ArrayList<String> options = mcontestdata.getOption();

        holder.mtxtview.setText(questionname);
        ShowOptionListAdapter mshowoptionlistadapter = new ShowOptionListAdapter(mans, options);
        holder.mrview.setAdapter(mshowoptionlistadapter);
    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    public class QuestionListView extends RecyclerView.ViewHolder{
        TextView mtxtview;
        RecyclerView mrview;
        public QuestionListView(@NonNull View itemView) {
            super(itemView);

            mtxtview = (TextView) itemView.findViewById(R.id.rowquestion_tv);
            mrview = (RecyclerView) itemView.findViewById(R.id.rowquestion_rv);
        }
    }
}
