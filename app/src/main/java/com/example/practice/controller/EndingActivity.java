package com.example.practice.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.practice.R;

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
        String text = "your score was " + mScore;
        mEnding.setText(text);

        mBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // Intent mainActivityIntent = new Intent(EndingActivity.this, MainActivity.class);
             //   startActivity(mainActivityIntent);

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