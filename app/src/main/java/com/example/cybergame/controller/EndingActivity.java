package com.example.cybergame.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cybergame.R;

public class EndingActivity extends AppCompatActivity {

    private TextView mEnding;
    private Button mBackToMain;
    public static final int REQUEST_CODE = 42;
    int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ending);

        Intent i = getIntent();
        mScore = i.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, -1);

        mBackToMain = findViewById(R.id.backToMain);
        mEnding = findViewById(R.id.ending);
        String text = "your score was " + mScore + "check out 'haveibeenpwned.com' - check if your details have been in a data breach. If they have... CHANGE THEM!!";
        mEnding.setText(text);

        mBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set result??
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