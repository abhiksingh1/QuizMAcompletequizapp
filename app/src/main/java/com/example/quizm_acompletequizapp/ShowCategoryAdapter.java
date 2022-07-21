package com.example.quizm_acompletequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowCategoryAdapter extends RecyclerView.Adapter<ShowCategoryAdapter.ShowCategoryList> {

    ArrayList<String> categorylist = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ShowCategoryAdapter(ArrayList<String> categorylist) {
        this.categorylist = categorylist;
    }

    @NonNull
    @Override
    public ShowCategoryList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater mlayoutinflater = LayoutInflater.from(parent.getContext());
        View mview = mlayoutinflater.inflate(R.layout.row_category, parent, false);
        return new ShowCategoryList(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowCategoryList holder, int position) {
        holder.category.setText(categorylist.get(position));
    }

    @Override
    public int getItemCount() {
        return categorylist.size();
    }

    public class ShowCategoryList extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView category;

        public ShowCategoryList(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            category = itemView.findViewById(R.id.rowcategory_tv_category);
        }

        @Override
        public void onClick(View v) {
            Integer index = getLayoutPosition();

            ArrayList<String> contestname = new ArrayList<String>();
            ArrayList<String> srcpath = new ArrayList<>();
            Toast.makeText(itemView.getContext(), categorylist.get(index), Toast.LENGTH_SHORT).show();

            db.collection(categorylist.get(index))
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            contestname.add(document.getData().keySet().toArray()[0].toString());
                            srcpath.add(document.getId());
                        }
                        Intent intent = new Intent(itemView.getContext(), ShowContestLists.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contestlist", contestname);
                        bundle.putSerializable("path", srcpath);
                        bundle.putString("category", categorylist.get(index));
                        intent.putExtras(bundle);
                        itemView.getContext().startActivity(intent);
                    } else {
                        Log.d("testing", "Error getting documents: ", task.getException());
                    }
                }
            });
        }
    }

}
