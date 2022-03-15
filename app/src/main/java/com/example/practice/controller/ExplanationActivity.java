package com.example.practice.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.practice.R;

public class ExplanationActivity extends AppCompatActivity {


    private Button mLetsPlayButton;
    public static final int EXPLANATION_RETURN_CODE= MainActivity.EXPLANATION_RETURN_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explanation);
        mLetsPlayButton = findViewById(R.id.letsplay);
        mLetsPlayButton.setEnabled(true);
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