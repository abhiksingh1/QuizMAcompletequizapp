package com.example.quizm_acompletequizapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizQuestionListView> {

    public static ArrayList<ContestData> questionlist = new ArrayList<>();

    public QuizAdapter(ArrayList<ContestData> questionlist) {
        this.questionlist = questionlist;
    }

    @NonNull
    @Override
    public QuizAdapter.QuizQuestionListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mlayoutinflater = LayoutInflater.from(parent.getContext());
        View mview = mlayoutinflater.inflate(R.layout.quiz_row_question, parent, false);
        return new QuizAdapter.QuizQuestionListView(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAdapter.QuizQuestionListView holder, int position) {

        ContestData mcontestdata = questionlist.get(position);
        String questionname = mcontestdata.getQuestion();
        ArrayList<Boolean> mans = mcontestdata.getAns();
        ArrayList<String> options = mcontestdata.getOption();

        holder.mtxtview.setText(questionname);
        QuizOptionAdapter mQuizOptionAdapter = new QuizOptionAdapter(mans, options);
        holder.mrview.setAdapter(mQuizOptionAdapter);
    }

    @Override
    public int getItemCount() {
        return questionlist.size();
    }

    public class QuizQuestionListView extends RecyclerView.ViewHolder{
        TextView mtxtview;
        RecyclerView mrview;
        public QuizQuestionListView(@NonNull View itemView) {
            super(itemView);

            mtxtview = (TextView) itemView.findViewById(R.id.quiz_rowquestion_tv);
            mrview = (RecyclerView) itemView.findViewById(R.id.quiz_rowquestion_rv);
        }
    }
}

