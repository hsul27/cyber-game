package com.example.cybergame.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cybergame.R;

public class ExplanationActivity extends AppCompatActivity {


    private Button mLetsPlayButton;
    public static final int EXPLANATION_RETURN_CODE= MainActivity.EXPLANATION_RETURN_CODE;
    private TextView mExplanation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);
        mLetsPlayButton = findViewById(R.id.letsplay);
        mLetsPlayButton.setEnabled(true);
        mExplanation = findViewById(R.id.explanation);
        mExplanation.setText("Congratulations!\n\nYou have been given a job at MediCo, a leading biopharmaceutical company that works to " +
                "develop and manufacture new medicinal products for various medical conditions.\n\nRecently, there have been a few strange " +
                "things happening, in both the manufacturing plant and office. All company staff have been given basic cyber security training, but they’re still " +
                "unsure about what exactly to look out for, and how to take action.\n\nYou’ll probably face a few tricky situations, as well as " +
                "some quickfire questions, so do your best and don’t panic. You’ll be a cyber expert in no time!\n\nLet’s see how you fare...\n\n" +
                "Good luck!");
        mLetsPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent();
                setResult(EXPLANATION_RETURN_CODE, r);
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

            System.out.println("ExplanationActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("ExplanationActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("ExplanationActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("ExplanationActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("ExplanationActivity::onDestroy()");
    }
}