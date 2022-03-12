package com.example.practice.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.practice.R;

public class ConsequenceActivity extends AppCompatActivity {

    private Button mBackToGame;
    private boolean mCorrectness;
    private TextView mExplanation;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consequence);

        mBackToGame = findViewById(R.id.backToGame);

        Intent i = getIntent();
        mCorrectness = i.getBooleanExtra(GameActivity.CORRECTNESS,false);
        if (mCorrectness == true) {
            result = "your answer was correct";
        } else if (mCorrectness == false) {
            result = "your answer was wrong";
        }

        mExplanation = findViewById(R.id.explanation);
        mExplanation.setText(result);


        mBackToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("EndingActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("EndingActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("EndingActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("EndingActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("EndingActivity::onDestroy()");
    }
}