package com.example.quizm_acompletequizapp;

import java.util.ArrayList;

public class ContestDataWithName {

    public String contestName;
    public ContestData contestData;

    public ContestDataWithName(String contestName, ContestData contestData) {
        this.contestName = contestName;
        this.contestData = contestData;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public ContestData getContestData() {
        return contestData;
    }

    public void setContestData(ContestData contestData) {
        this.contestData = contestData;
    }
}
