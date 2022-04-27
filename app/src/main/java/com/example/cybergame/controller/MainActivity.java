package com.example.cybergame.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cybergame.R;
import com.example.cybergame.model.User;

public class MainActivity extends AppCompatActivity {

    //return codes used to check which intent have come back from
    public static final int GAME_RETURN_CODE = 3;
    public static final int EXPLANATION_RETURN_CODE = 2;
    private TextView mWelcomeTextView;
    private EditText mNameInput;
    private Button mPlayButton;
    private TextView mCurrentScores;
    private TextView mShowScores;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //widget data set
        mWelcomeTextView = findViewById(R.id.welcometext);
        mNameInput = findViewById(R.id.nameinput);
        mPlayButton = findViewById(R.id.playbutton);
        mPlayButton.setEnabled(false);
        mCurrentScores = findViewById(R.id.currentscores);
        mShowScores = findViewById(R.id.showscores);

        mPreferences = getPreferences(MODE_PRIVATE); //not accessible outside of application, data kept private
        User mUser = new User();

        displayScores();

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //if user has entered data in name field, they can then press button
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
                mUser.setFirstName(firstname); //sets current user's name to the name entered

                Intent explanationActivityIntent = new Intent(MainActivity.this, ExplanationActivity.class);
                startActivityForResult(explanationActivityIntent, EXPLANATION_RETURN_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case EXPLANATION_RETURN_CODE: //if the explanation screen has just been shown
                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                gameActivityIntent.putExtra("name", mNameInput.getText().toString()); //pass user's name to GameActivity
                startActivityForResult(gameActivityIntent, GAME_RETURN_CODE); //start the game
            case GAME_RETURN_CODE: //if the game has ended
                int score = data.getIntExtra("BUNDLE_EXTRA_SCORE", 0); //retrieve user's score
                setScore(score);
                displayScores();
        }
    }

    private void setScore(int score) {
        SharedPreferences.Editor myEdit = mPreferences.edit(); //create new shared preferences editor
        String name = mNameInput.getText().toString();

        //if player already exists and new score is better than old score
        if(mPreferences.contains(name) && score > mPreferences.getInt(name, 0))
        {
            myEdit.putInt(name, score); //change the score
        }
        else { //add new user
            myEdit.putString(name, name);
            myEdit.putInt(name, score);
        }
        myEdit.apply(); //apply changes
    }

    private void displayScores() {
        //formatting data from shared preferences format
        String allDetails = mPreferences.getAll().toString();
        String withoutb = allDetails.replaceAll("[{}]", "");
        String withoutc = withoutb.replace(',','\n');
        String withoute = withoutc.replaceAll("[=]", ": ");
        mShowScores.setText(withoute);
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