package com.example.practice.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.practice.R;

public class EndingActivity extends AppCompatActivity {

    String mScore = getIntent().getStringExtra("SCORE");
    private TextView mEnding;
    private Button mBackToMain;
    public static final int REQUEST_CODE = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

    mBackToMain = findViewById(R.id.backToMain);
    mEnding = findViewById(R.id.ending);
    String text = "your score was " + mScore;
    mEnding.setText(text);
    mBackToMain.setEnabled(true);
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