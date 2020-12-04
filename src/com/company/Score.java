package com.company;

import com.company.frame.MainFrame;

public class Score {
    int score =0;

    public void plus(int i){
        score+=i;
        MainFrame.label.setText(Integer.toString(score));
    }
    public void minus(int i){
        score-=i;
        MainFrame.label.setText(Integer.toString(score));
    }
}
