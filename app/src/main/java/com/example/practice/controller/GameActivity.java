package com.example.practice.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.example.practice.R;
import com.example.practice.model.Question;
import com.example.practice.model.QuestionBank;


import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mGameQuestionTextView;
    private Button mGameButton1;
    private Button mGameButton2;
    private Button mGameButton3;
    private Button mGameButton4;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    public static final String CORRECTNESS = "correctness";
    public static final String EXPLANATION = "explanation";

    private boolean mEnableTouchEvents;
    public static final int GAME_RETURN_CODE = MainActivity.GAME_RETURN_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println("GameActivity::onCreate()");

        mQuestionBank = this.generateQuestions();

        if(savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 5;
        }

        mEnableTouchEvents = true;

        mGameQuestionTextView = findViewById(R.id.gameQuestionText);
        mGameButton1 = findViewById(R.id.gameAnswer1btn);
        mGameButton2 = findViewById(R.id.gameAnswer2btn);
        mGameButton3 = findViewById(R.id.gameAnswer3btn);
        mGameButton4 = findViewById(R.id.gameAnswer4btn);

        mGameButton1.setTag(0);
        mGameButton2.setTag(1);
        mGameButton3.setTag(2);
        mGameButton4.setTag(3);

        mGameButton1.setOnClickListener(GameActivity.this);
        mGameButton2.setOnClickListener(GameActivity.this);
        mGameButton3.setOnClickListener(GameActivity.this);
        mGameButton4.setOnClickListener(GameActivity.this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);
        super.onSaveInstanceState(outState);
    }

    private void displayQuestion(final Question question) {
        mGameQuestionTextView.setText(question.getQuestion());
        mGameButton1.setText(question.getChoiceList().get(0));
        mGameButton2.setText(question.getChoiceList().get(1));
        mGameButton3.setText(question.getChoiceList().get(2));
        mGameButton4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("A computer network security system that restricts internet traffic in, out, or within a private network", Arrays.asList("Protocol", "Firewall", "Encryption", "Cookies"), 1, "well done, firewall is correct - make sure you have installed a firewall etcetc", "the answer is firewall, this is why...");

        Question question2 = new Question("Which one of the following refers to the technique used for verifying the integrity of the message?", Arrays.asList("Protocol", "Decryption algorithm", "Message digest", "Digital signature"), 2, "well done - look into message digesting and adding digital signature", "the answer is message digest - this is etc...");

        Question question3 = new Question("In Wi-Fi Security, which of the following protocol is most commonly used?", Arrays.asList("WPS", "WPA2", "WEP", "WPA"), 1, "well done - wpa3 is on its way now", "wrong - wpa2 is most commonly used as...");

        Question question4 = new Question("Encryption techniques are primarily used for improving...", Arrays.asList("Longevity", "Reliability", "Performance", "Security"), 3, "well done - encryption is a basic measure to ensure...", "answer is encryption - method of securing data ...");

        Question question5 = new Question("Which of the following is considered as the world's first antivirus program?", Arrays.asList("Creeper", "Reaper", "Tinkered", "Ray Tomlinson"), 1, "well done - reaper was developed in ...", "answer is reaper - it was developed in ... by...");

        Question question6 = new Question("Which type of the following malware does not replicate or clone themselves through infection?", Arrays.asList("Rootkits", "Worms", "Viruses", "Trojans"), 3, "well done - EXPLAIN HERE EXPLAIN HERE", "the answer is trojans - all the others can replicate themselves by...");

        Question question7 = new Question("Which of the following malware types allows the attacker to access the administrative controls and enables them to do almost anything they want with the infected computers?", Arrays.asList("RATs", "Worms", "Rootkits", "Botnets"), 0, "well done - remote access trojans", "incorrect - remote access trojans are... ");

        Question question8 = new Question("DNS translates a domain name into...", Arrays.asList("Hex", "Binary", "IP", "URL"), 3, "well done - its converted by ...", "incorrect - it's URL - this is done by...");

        Question question9 = new Question("Which one of the following is considered as the most secure Linux operating system that also provides anonymity and the incognito option for securing the user's information?", Arrays.asList("Ubuntu", "Tails", "Fedora", "Debian"), 1, "well done - tails is correct, EXPLAIN EXPLAIN", "incorrect - answer is tails bc ...");

        Question question10 = new Question("Which of the following is not a type of phishing?", Arrays.asList("vishing", "smishing", "whaling", "finning"), 3, "well done - the others are all forms of phishing that you could face", "incorrect - answer is finning. the rest are forms of phishing");

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5));
    }

    @Override
    public void onClick(View v) {


        mEnableTouchEvents = false;

        if (--mNumberOfQuestions == 0) {
            endGame();
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mEnableTouchEvents = true;

                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }, 200); // LENGTH_SHORT is usually 2 second long
        }

        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            displayConsequence(true, mCurrentQuestion.getCorrectConsequence());
            mScore++;
        } else {
            // Wrong answer
            displayConsequence(false, mCurrentQuestion.getIncorrectConsequence());
        }
    }

    public void displayConsequence(boolean correct, String explain) {
        //open consequence activity, read consequence, then return to activity
        Intent consequence = new Intent(GameActivity.this, ConsequenceActivity.class);
        consequence.putExtra(CORRECTNESS, Boolean.valueOf(correct));
        consequence.putExtra(EXPLANATION, explain);
        startActivity(consequence);
    }


    public void endGame() {

        Intent r = new Intent();
        r.putExtra(BUNDLE_EXTRA_SCORE, mScore);

        Intent endingActivityIntent = new Intent(GameActivity.this, EndingActivity.class);
        endingActivityIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
        startActivity(endingActivityIntent);

        setResult(GAME_RETURN_CODE, r);
        finish();
    //finish

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//        builder.setTitle("Well done!")
//                .setMessage("Your score is " + mScore)
//                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // End the activity
//                        Intent resultIntent = new Intent();
//                        resultIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
//                        setResult(RESULT_OK, resultIntent);
//                        finish();
//                    }
//                })
//                .setCancelable(false)
//                .create()
//                .show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}