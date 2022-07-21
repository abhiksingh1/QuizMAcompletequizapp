package com.example.quizm_acompletequizapp;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowContestListsAdapter extends RecyclerView.Adapter<ShowContestListsAdapter.ShowContestListsAdapterViewHolder> {

    ArrayList<String> contestList = new ArrayList<>();

    public ShowContestListsAdapter(ArrayList<String> contestList) {
        this.contestList = contestList;
    }

    @NonNull
    @Override
    public ShowContestListsAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contest, parent, false);

        return new ShowContestListsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowContestListsAdapterViewHolder holder, int position) {
        holder.contestName.setText(contestList.get(position));

    }

    @Override
    public int getItemCount() {
        return contestList.size();
    }

    public class ShowContestListsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView contestName;

        public ShowContestListsAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            contestName = itemView.findViewById(R.id.rowcontest_tv_contestname);
        }

        @Override
        public void onClick(View v) {
            ArrayList<String> srcpath = new ArrayList<>();
            srcpath = ShowContestLists.getSrcpath();
            String categoryName = ShowContestLists.getCategory().toString();
            Integer index = getLayoutPosition();
            Toast.makeText(itemView.getContext(), "Name " + categoryName, Toast.LENGTH_SHORT).show();

            //if (getLayoutPosition() == 0) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                DocumentReference docRef = db.collection(categoryName).document(srcpath.get(index));
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String key = document.getData().keySet().toArray()[0].toString();
                                Gson gson = new Gson();
                                ArrayList<ContestData> data = new ArrayList<>();
                                ArrayList<ContestData> arrayList = new ArrayList<>();
                                data = (ArrayList<ContestData>) document.get(key);
                                for (int i = 0; i < data.size(); i++) {
                                    JsonElement jsonElement = gson.toJsonTree(data.get(i));
                                    ContestData pojo = gson.fromJson(jsonElement, ContestData.class);
                                    arrayList.add(pojo);
                                }

                                Intent intent = new Intent(itemView.getContext(), Quiz.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("list", arrayList);
                                intent.putExtras(bundle);
                                itemView.getContext().startActivity(intent);
                            } else {
                                Toast.makeText(itemView.getContext(), "No document present!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(itemView.getContext(), "Error: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
           // }
        }
    }

}
