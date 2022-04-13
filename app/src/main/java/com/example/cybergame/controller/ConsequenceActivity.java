package com.example.cybergame.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cybergame.R;

public class ConsequenceActivity extends AppCompatActivity {

    private Button mBackToGame;
    private boolean mCorrectness;
    private TextView mConsequence;
    private String result;
    private String mExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consequence);

        mBackToGame = findViewById(R.id.backToGame);

        mCorrectness = getIntent().getBooleanExtra(GameActivity.CORRECTNESS, true);

        mExplanation = getIntent().getStringExtra(GameActivity.EXPLANATION);
        if (mCorrectness == true) {
            result = "Your answer was correct... \n\n" + mExplanation;
        } else if (mCorrectness == false) {
            result = "Your answer was wrong... \n\n" + mExplanation;
        }

        mConsequence = findViewById(R.id.consequence);
        mConsequence.setText(result);


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