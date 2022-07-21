package com.example.quizm_acompletequizapp;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShowOptionListAdapter extends RecyclerView.Adapter<ShowOptionListAdapter.OptionListView>  {

    ArrayList<Boolean> manslist;
    ArrayList<String> moptionlist;

    public ShowOptionListAdapter(ArrayList<Boolean> manslist, ArrayList<String> moptionlist) {
        this.manslist = manslist;
        this.moptionlist = moptionlist;
    }

    @NonNull
    @Override
    public OptionListView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mlayoutinflator = LayoutInflater.from(parent.getContext());
        View mview = mlayoutinflator.inflate(R.layout.row_option, parent, false);
        return new OptionListView(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionListView holder, int position) {
        holder.mchbox.setChecked(manslist.get(position));
        holder.mtxtview.setText(moptionlist.get(position));
    }

    @Override
    public int getItemCount() {
        return moptionlist.size();
    }

    public class OptionListView extends RecyclerView.ViewHolder {
        AppCompatCheckBox mchbox;
        TextView mtxtview;
        public OptionListView(@NonNull View itemView) {
            super(itemView);
            mchbox = itemView.findViewById(R.id.rowoption_chbox);
            mtxtview = (TextView) itemView.findViewById(R.id.rowoption_tv);
        }
    }
}
