package com.example.practice.model;

import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;
    private String mCorrectConsequence;
    private String mIncorrectConsequence;

    public Question(String question, List<String> choiceList, int answerIndex, String correctConsequence, String incorrectConsequence) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
        this.setCorrectConsequence(correctConsequence);
        this.setIncorrectConsequence(incorrectConsequence);
    }

    public String getQuestion() {
        return mQuestion;
    }
    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }
    public void setChoiceList(List<String> choiceList) {
        if (choiceList == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }
    public void setAnswerIndex(int answerIndex) {
        if (answerIndex < 0 || answerIndex >= mChoiceList.size()) {
            throw new IllegalArgumentException("Answer index is out of bound");
        }
        mAnswerIndex = answerIndex;
    }

    public String getCorrectConsequence() {
        return mCorrectConsequence;
    }

    public void setCorrectConsequence(String correctConsequence) {
        mCorrectConsequence = correctConsequence;
    }

    public String getIncorrectConsequence() {
        return mIncorrectConsequence;
    }

    public void setIncorrectConsequence(String incorrectConsequence) {
        mIncorrectConsequence = incorrectConsequence;
    }

    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                ", mCorrectConsequence='" + mCorrectConsequence + '\'' +
                ", mIncorrectConsequence='" + mIncorrectConsequence + '\'' +
                '}';
    }
}
