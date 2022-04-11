package com.example.practice.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.practice.R;
import com.example.practice.model.User;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final int GAME_RETURN_CODE = 3;
    public static final int EXPLANATION_RETURN_CODE = 2;
    private TextView mWelcomeTextView;
    private EditText mNameInput;
    private Button mPlayButton;
    private TextView mCurrentScores;
    private TextView mShowScores;
    private SharedPreferences mPreferences;
 //   public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
  //  public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeTextView = findViewById(R.id.welcometext);
        mNameInput = findViewById(R.id.nameinput);
        mPlayButton = findViewById(R.id.playbutton);
        mPlayButton.setEnabled(false);
        mCurrentScores = findViewById(R.id.currentscores);
        mShowScores = findViewById(R.id.showscores);

        mPreferences = getPreferences(MODE_PRIVATE); //not accessible outside of application
        User mUser = new User();

       // helloUser();

        displayScores();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = mNameInput.getText().toString();
                mUser.setFirstName(firstname);

                Intent explanationActivityIntent = new Intent(MainActivity.this, ExplanationActivity.class);
                startActivityForResult(explanationActivityIntent, EXPLANATION_RETURN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case EXPLANATION_RETURN_CODE:
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_RETURN_CODE);
            case GAME_RETURN_CODE:
                int score = data.getIntExtra("BUNDLE_EXTRA_SCORE", -1);
                setScore(score);
               // helloUser();
        }
    }

//    private void helloUser() {
//        String firstName = mPreferences.getString(PREF_KEY_FIRSTNAME, null);
//        if(firstName != null) {
//            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
//
//            String text = "welcome back " + firstName
//                    + "\nyour highest score is " + score
//                    + ", try improve!!";
//            mWelcomeTextView.setText(text);
//            mNameInput.setText(firstName);
//            mNameInput.setSelection(firstName.length());
//            mPlayButton.setEnabled(true);
//        }
//    }

    private void setScore(int score) {
        SharedPreferences.Editor myEdit = mPreferences.edit(); //create new edit
        String name = mNameInput.getText().toString();
        if(mPreferences.contains(name) && score > mPreferences.getInt(name, -1)) //if player already exists and new score is better than old
        {
            myEdit.putInt(name, score); //change the score
        }
        else {
            myEdit.putString(name, name);
            myEdit.putInt(name, score);
        }
        myEdit.commit();
    }

    private void displayScores() {
        Map allDetails = mPreferences.getAll();
        System.out.println(mPreferences.getAll());
        String toPrint = "";
        Iterator<Integer> itr = allDetails.keySet().iterator();
        while (itr.hasNext()) {
            toPrint = toPrint + itr.next() + '\n';
        }
        mShowScores.setText(toPrint);
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }
}