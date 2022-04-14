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
        String ranking = "";
        if (mScore >= 7) {
            ranking = "Great! MediCo needs you on their cyber security team ASAP!";
        } else if (mScore < 7 && mScore >= 4) {
            ranking = "Well done! You did well. You still have some more to learn, but you've got a good idea of the basics.";
        } else if (mScore <= 3) {
            ranking = "Hmm. You may need some more training...";
        }


        mBackToMain = findViewById(R.id.backToMain);
        mEnding = findViewById(R.id.ending);
        String text = "Your score was " + mScore + "\n\n"+ranking+"\n\nIt's important that everyone has some basic cyber security awareness, especially because we live in times " +
                "where cyberattacks are becoming and increasingly popular method in wars even!\n\nCyber attacks can vary in scale and maliciousness, but if you implement the " +
                "right tools and know what to look out for, you better your chances of avoiding them.\n\n\n\n\n\nP.S. check out 'haveibeenpwned.com'\n\nThis site lets you check if your details" +
                " have been in a data breach.\nIf they have... CHANGE THEM!!";
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