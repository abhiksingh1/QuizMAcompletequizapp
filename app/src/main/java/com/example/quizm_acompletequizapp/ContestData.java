package com.example.quizm_acompletequizapp;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ContestData implements Serializable {

    public String question;
    public ArrayList<Boolean> ans;
    public ArrayList<String> option;

    public ContestData(String question, ArrayList<Boolean> ans, ArrayList<String> option) {
        this.question = question;
        this.ans = ans;
        this.option = option;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Boolean> getAns() {
        return ans;
    }

    public void setAns(ArrayList<Boolean> ans) {
        this.ans = ans;
    }

    public ArrayList<String> getOption() {
        return option;
    }

    public void setOption(ArrayList<String> option) {
        this.option = option;
    }

}
