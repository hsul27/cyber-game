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

    private boolean mEnableTouchEvents;

    public static final int REQUEST_CODE = 44;

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
            mNumberOfQuestions = 10;
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
        Question question1 = new Question("What is Spiderman's real name?", Arrays.asList("Tony Stark", "Peter Parker", "Happy Hogan", "Ned Leeds"), 1);

        Question question2 = new Question("I love you...", Arrays.asList("1,000,000", "to infinity and beyond", "3,000", "lots"), 2);

        Question question3 = new Question("What did Black Widow die to get?", Arrays.asList("Time stone", "Soul stone", "Mind stone", "Space stone"), 1);

        Question question4 = new Question("Who appeared in No Way Home?", Arrays.asList("Deadpool", "Hawkeye", "Scarlet Witch", "Daredevil"), 3);

        Question question5 = new Question("What was Steven Strange's career before he became magical?", Arrays.asList("Surgeon", "Artist", "Nurse", "Engineer"), 0);

        Question question6 = new Question("What type of radiation caused Bruce Banner to become the Hulk?", Arrays.asList("Infrared", "Gamma", "Ultraviolet", "Microwave"), 1);

        Question question7 = new Question("On what planet is the soul stone found?", Arrays.asList("Titan", "Morag", "Xandar", "Vormir"), 3);

        Question question8 = new Question("Who is the rock dude from Thor Ragnarok?", Arrays.asList("Smaug", "Groot", "Korg", "Drax"), 2);

        Question question9 = new Question("Who did not disappear during the blip?", Arrays.asList("Captain America", "Black Panther", "Spiderman", "Antman"), 0);

        Question question10 = new Question("Who isn't a Spiderman villain?", Arrays.asList("Electro", "Winter Soldier", "Sandman", "Mysterio"), 1);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10));
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        boolean answerCorrect;
        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            answerCorrect = true;
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            // Wrong answer
            answerCorrect = false;
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

        displayConsequence(answerCorrect);

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
            },200); // LENGTH_SHORT is usually 2 second long
        }

    }

    public void displayConsequence(boolean correct) {
        //open consequence activity, read consequence, then return to activity

        Intent i = new Intent(GameActivity.this, ConsequenceActivity.class);
        i.putExtra(CORRECTNESS, correct);
        startActivity(i);
    }


    public void endGame() {

    Intent endingActivityIntent = new Intent(GameActivity.this, EndingActivity.class);
    endingActivityIntent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
    startActivity(endingActivityIntent);

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