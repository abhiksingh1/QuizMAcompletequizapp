package com.example.quizm_acompletequizapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.WriteResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateContest extends AppCompatActivity {

    TextView mtv;
    Button mcancel;
    Dialog mdialog;
    EditText mnameofcontest;

    ArrayList<ContestData> contestlist = new ArrayList<>();

    FirebaseFirestore db;

    String[] category = {"General", "Science", "Mathematics", "Biology", "Physics", "Chemistry"};
    Spinner spinner;

    String categoryValue = "General", creatorName = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contest);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        mtv = findViewById(R.id.createcontest_tv_quesnums);
        mcancel = findViewById(R.id.questionpopup_btn_cancel);
        spinner = findViewById(R.id.createcontest_spin_category);
        db = FirebaseFirestore.getInstance();

        mnameofcontest = findViewById(R.id.createcontest_edt_nameofcontest);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, category);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryValue = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "CategoryValue = " + categoryValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "Nothing is selected !", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void ShowDialog(View v) {
        mdialog = new Dialog(CreateContest.this);
        mdialog.setContentView(R.layout.question_pop_up);
        mdialog.setTitle("Title...");

        LinearLayout layoutparent = (LinearLayout) mdialog.findViewById(R.id.question_ll_layoutmain);
        Button btnaddoption = (Button) mdialog.findViewById(R.id.questionpopup_btn_addoption);
        Button btnsaveques = (Button) mdialog.findViewById(R.id.questionpopup_btn_save);
        LinearLayout mlinearlayout = (LinearLayout) mdialog.findViewById(R.id.question_ll_layoutmain);
        EditText edtquestion = (EditText) mdialog.findViewById(R.id.questionpopup_edt_ques);

        btnsaveques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///ContestData contestData = new ContestData();

                //Log.i("ch", " Question is = " + edtquestion.getText().toString());
                ///contestData.setQuestion(edtquestion.getText().toString());
                ArrayList<Boolean> mans = new ArrayList<Boolean>();
                ArrayList<String> moption = new ArrayList<String>();
                for (int i = 0; i < mlinearlayout.getChildCount(); i++) {
                    View element = mlinearlayout.getChildAt(i);
                    CheckBox cb = (CheckBox) element.findViewById(R.id.optionchbox);
                    EditText txt = (EditText) element.findViewById(R.id.optionedt);
                    //Log.i("ch", "iteration " + i + " " + txt.getText().toString());
                    moption.add(txt.getText().toString());
                    if (cb.isChecked()) {
                        //Log.i("ch", "iteration " + i + " => " + txt.getText().toString());
                        mans.add(true);
                    } else {
                        mans.add(false);
                    }
                }
                ///contestData.setOption(moption);
                //contestData.setAns(mans);
                ContestData contestData = new ContestData(edtquestion.getText().toString(), mans, moption);
                contestlist.add(contestData);
                mdialog.dismiss();
            }
        });
        btnaddoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LinearLayout layoutchild = new LinearLayout(CreateContest.this);
                layoutchild.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layoutchild.setOrientation(LinearLayout.HORIZONTAL);
                layoutparent.addView(layoutchild);

                CheckBox chboxopt = new CheckBox(CreateContest.this);
                chboxopt.setId(R.id.optionchbox);
                EditText edtopt = new EditText(CreateContest.this);
                edtopt.setId(R.id.optionedt);
                edtopt.setHint("Enter option here");
                ImageView img = new ImageView(CreateContest.this);
                img.setImageResource(R.drawable.ic_baseline_cancel_24);

                layoutchild.addView(chboxopt);
                layoutchild.addView(edtopt);
                layoutchild.addView(img);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        layoutparent.removeView(layoutchild);
                    }
                });
            }
        });
        mdialog.show();
        Window window = mdialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void Cancel(View view) {
        mdialog.dismiss();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(CreateContest.this, SignIn.class);
        startActivity(intent);
    }

    public void ShowQuestions(View view) {
        if (contestlist.size() == 0) {
            Toast.makeText(getApplicationContext(), "There is no question to show, Please add question first.", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("testing", "hello ji :" + contestlist);
        Intent intent = new Intent(CreateContest.this, ShowQuesList.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", contestlist);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void SaveContest(View view) {

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//        Log.i("ch", "Current user = " + email.substring(0, email.indexOf("@")) + " and name of contest is = " + mnameofcontest.getText().toString());

        Map<String, Object> data = new HashMap<>();
        data.put(mnameofcontest.getText().toString(), contestlist);
        db.collection(categoryValue)
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Successfuly contest is added.", Toast.LENGTH_SHORT);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    }
                });

    }

}