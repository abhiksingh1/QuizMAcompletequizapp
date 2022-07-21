package com.example.quizm_acompletequizapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class QuizOptionAdapter extends RecyclerView.Adapter<QuizOptionAdapter.QuizOptionListView > {

    ArrayList<Boolean> manslist = new ArrayList<>();
    ArrayList<String> moptionlist = new ArrayList<>();
    //ArrayList<Boolean> mscorelist;

    public QuizOptionAdapter(ArrayList<Boolean> manslist, ArrayList<String> moptionlist) {
        this.manslist = manslist;
        this.moptionlist = moptionlist;
    }

    @NonNull
    @Override
    public QuizOptionAdapter.QuizOptionListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mlayoutinflator = LayoutInflater.from(parent.getContext());
        View mview = mlayoutinflator.inflate(R.layout.quiz_row_option, parent, false);
        return new QuizOptionAdapter.QuizOptionListView (mview);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizOptionAdapter.QuizOptionListView  holder, int position) {
        manslist.set(position, false);
        holder.mchbox.setChecked(manslist.get(position));
        holder.mtxtview.setText(moptionlist.get(position));
        holder.mchbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //mscorelist.set(holder.getAdapterPosition(), isChecked);
                  manslist.set(holder.getAdapterPosition(), isChecked);
                //Toast.makeText(holder.itemView.getContext(), "clicked = " + mscorelist.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(holder.itemView.getContext(), "clicked = " + mscorelist, Toast.LENGTH_SHORT).show();
                //Log.d("shivay", "stored" + mscorelist);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moptionlist.size();
    }

    public  class QuizOptionListView extends RecyclerView.ViewHolder {
        AppCompatCheckBox mchbox;
        TextView mtxtview;
        public QuizOptionListView(@NonNull View itemView) {
            super(itemView);
            mchbox = itemView.findViewById(R.id.quiz_rowoption_chbox);
            mtxtview = (TextView) itemView.findViewById(R.id.quiz_rowoption_tv);
            //mscorelist = new ArrayList<Boolean>(Collections.nCopies(manslist.size(), Boolean.FALSE));
        }
    }


}